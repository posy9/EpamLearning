package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public final class BrandRepository extends CommonRepository<Brand> {

    private static final List<String> fields = List.of("name");

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Brand.class, id));
    }

    @Override
    public List<Brand> readMultiple(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
        Root<Brand> root = cq.from(Brand.class);
        addOrderBy(pageable, root, cb, cq, fields);
        TypedQuery<Brand> query = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public Optional<Brand> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
        Root<Brand> root = cq.from(Brand.class);
        cq.where(cb.equal(root.get("name"), name));
        TypedQuery<Brand> query = entityManager.createQuery(cq);
        List<Brand> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }
}
