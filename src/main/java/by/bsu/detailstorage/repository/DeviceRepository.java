package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Device;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public final class DeviceRepository extends CommonRepository<Device> implements AbstractRepository<Device> {

    private static final String MODEL_FIELD = "model";
    private static final String BRAND_FIELD = "brand";
    private static final List<String> fields = List.of(BRAND_FIELD, MODEL_FIELD, "category");

    @Override
    public Optional<Device> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Device.class, id));
    }

    @Override
    public List<Device>  readMultiple(Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> cq = cb.createQuery(Device.class);
        Root<Device> root = cq.from(Device.class);
        addOrderBy(pageable, root, cb, cq, fields);
        TypedQuery<Device> query = entityManager.createQuery(cq)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    public Optional<Device> findByUniqueCouple(String model, Brand brand) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> cq = cb.createQuery(Device.class);
        Root<Device> root = cq.from(Device.class);
        cq.where(cb.and(cb.equal(root.get(MODEL_FIELD), model), cb.equal(root.get(BRAND_FIELD), brand)));
        TypedQuery<Device> query = entityManager.createQuery(cq);
        List<Device> result = query.getResultList();
        return result.isEmpty() ? Optional.empty() : Optional.of(result.getFirst());
    }
}
