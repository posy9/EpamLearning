package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Category;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public final class CategoryRepository extends CommonRepository<Category> {

    private static final List<String> fields = List.of("name");

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.of(entityManager.find(Category.class,id));
    }

    @Override
    public List<Category> readMultiple(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        addOrderBy(pageable, root, cb, cq, fields);
        TypedQuery<Category> query = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
}
