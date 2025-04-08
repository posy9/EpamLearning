package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Country;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.COUNTRY;

@Repository
public final class CountryRepository extends CommonRepository<Country> {

    private static final String COUNTRY_ALIAS = "c";
    private static final String SELECT_COUNTRY_STATEMENT = String.format(SELECT_FROM, COUNTRY_ALIAS,
            COUNTRY.getEntityName(), COUNTRY_ALIAS);

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(entityManager.find(Country.class, id));
    }

    @Override
    public List<Country> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> country = cq.from(Country.class);
        CriteriaQuery<Country> select = cq.select(country);
        TypedQuery<Country> query = entityManager.createQuery(select) ;
        return query.getResultList();
    }
}
