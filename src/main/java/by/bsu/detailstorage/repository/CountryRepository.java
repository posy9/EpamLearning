package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Country;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public final class CountryRepository extends CommonRepository<Country> {

    private static final List<String> fields = List.of("name");

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(entityManager.find(Country.class, id));
    }

    @Override
    public List<Country> readMultiple(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> root = cq.from(Country.class);
        addOrderBy(pageable, root, cb, cq, fields);
        TypedQuery<Country> query = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }
}
