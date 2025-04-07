package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.model.Device;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DEVICE;

@Repository
public final class DeviceRepository extends CommonRepository<Device> implements PageableEntitiesRepository<Device> {

    private static final String DEVICE_ALIAS = "d";
    private static final String SELECT_DEVICE_STATEMENT = String.format(SELECT_FROM,DEVICE_ALIAS,
            DEVICE.getEntityName(), DEVICE_ALIAS);

    @Override
    public Optional<Device> findById(Long id) {
        return Optional.of(entityManager.find(Device.class, id));
    }

    @Override
    public List<Device> findAll() {
        TypedQuery<Device> query = entityManager.createQuery(SELECT_DEVICE_STATEMENT, Device.class);
        return query.getResultList();
    }

    @Override
    public List<Device>  readMultiple(Pageable pageable) {
        String resultStatementPrepared = getResultStatement(pageable);
        TypedQuery<Device> query = entityManager.createQuery(resultStatementPrepared, Device.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize());
        return query.getResultList();
    }

    private String getResultStatement(Pageable pageable) {
        if(pageable.getSort().isSorted()){
            return makeStatementWithGroupBy(pageable);
        }
        else{
            return SELECT_DEVICE_STATEMENT;
        }
    }

    private String makeStatementWithGroupBy(Pageable pageable) {
        List<String> orders = new ArrayList<>();
        for (Sort.Order sort : pageable.getSort()) {
            orders.add(DEVICE_ALIAS + DOT + sort.getProperty() + SPACE + sort.getDirection());
        }
        return SELECT_DEVICE_STATEMENT + SPACE + ORDER_BY + SPACE + String.join(COMMA + SPACE, orders);
    }
}
