package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Category;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.CATEGORY;

@Repository
public final class CategoryRepository extends CommonRepository<Category> {

    private static final String CATEGORY_ALIAS = "c";
    private static final String SELECT_CATEGORY_STATEMENT = String.format(SELECT_FROM,CATEGORY_ALIAS,
            CATEGORY.getEntityName(), CATEGORY_ALIAS);

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.of(entityManager.find(Category.class,id));
    }

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = entityManager.createQuery(SELECT_CATEGORY_STATEMENT, Category.class);
        return query.getResultList();
    }
}
