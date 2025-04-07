package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Country;
import jakarta.persistence.TypedQuery;
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
        TypedQuery<Country> query = entityManager.createQuery(SELECT_COUNTRY_STATEMENT, Country.class);
        return query.getResultList();
    }

}
