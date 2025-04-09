package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.model.Detail;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public final class DetailRepository extends CommonRepository<Detail> {

    private static final String NAME_FIELD = "name";
    private static final List<String> fields = List.of(NAME_FIELD, "device", "country", "amount", "type");

    @Override
    public Optional<Detail> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Detail.class, id));
    }

    @Override
    public List<Detail>  readMultiple(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Detail> cq = cb.createQuery(Detail.class);
        Root<Detail> root = cq.from(Detail.class);
        addOrderBy(pageable, root, cb, cq, fields);
        TypedQuery<Detail> query = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public Optional<Detail> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Detail> cq = cb.createQuery(Detail.class);
        Root<Detail> root = cq.from(Detail.class);
        cq.where(cb.equal(root.get(NAME_FIELD), name));
        TypedQuery<Detail> query = entityManager.createQuery(cq);
        List<Detail> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }
}
