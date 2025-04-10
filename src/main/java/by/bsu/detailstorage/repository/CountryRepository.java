package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Category;
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
public class CountryRepository extends CommonRepository<Country> {

    private static final String NAME_FIELD = "name";
    private static final List<String> fields = List.of(NAME_FIELD);

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Country.class, id));
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

    public Optional<Country> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> root = cq.from(Country.class);
        cq.where(cb.equal(root.get(NAME_FIELD), name));
        TypedQuery<Country> query = entityManager.createQuery(cq);
        List<Country> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }
}
