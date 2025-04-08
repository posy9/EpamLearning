package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.model.Type;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public final class TypeRepository extends CommonRepository<Type> {

    private static final List<String> fields = List.of("name");

    @Override
    public Optional<Type> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Type.class, id));
    }

    @Override
    public List<Type> readMultiple(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Type> cq = cb.createQuery(Type.class);
        Root<Type> root = cq.from(Type.class);
        addOrderBy(pageable, root, cb, cq, fields);
        TypedQuery<Type> query = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public Optional<Type> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Type> cq = cb.createQuery(Type.class);
        Root<Type> root = cq.from(Type.class);
        cq.where(cb.equal(root.get("name"), name));
        TypedQuery<Type> query = entityManager.createQuery(cq);
        List<Type> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }
}
