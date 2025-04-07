package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.BRAND;


@Repository
public final class BrandRepository extends CommonRepository<Brand> {

    private static final String BRAND_ALIAS = "b";
    private static final String SELECT_BRAND_STATEMENT = String.format(SELECT_FROM,BRAND_ALIAS,
            BRAND.getEntityName(), BRAND_ALIAS);


    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.of(entityManager.find(Brand.class, id));
    }

    @Override
    public List<Brand> findAll() {
        TypedQuery<Brand> query = entityManager.createQuery(SELECT_BRAND_STATEMENT, Brand.class);
        return query.getResultList();
    }


}
