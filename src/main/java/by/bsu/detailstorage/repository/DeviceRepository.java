package by.bsu.detailstorage.repository;

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

    private static final List<String> fields = List.of("brand", "model", "category");

    @Override
    public Optional<Device> findById(Long id) {
        return Optional.of(entityManager.find(Device.class, id));
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
}
