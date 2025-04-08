package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Device;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.context.annotation.Import;
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> cq = cb.createQuery(Brand.class);
        Root<Brand> brand = cq.from(Brand.class);
        CriteriaQuery<Brand> select = cq.select(brand);
        TypedQuery<Brand> query = entityManager.createQuery(select) ;
        return query.getResultList();
    }
}
