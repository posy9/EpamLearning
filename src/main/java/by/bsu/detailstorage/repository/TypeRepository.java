package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Type;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.TYPE;

@Repository
public final class TypeRepository extends CommonRepository<Type> {

    private static final String TYPE_ALIAS = "t";
    private static final String SELECT_TYPE_STATEMENT = String.format(SELECT_FROM, TYPE_ALIAS,
            TYPE.getEntityName(), TYPE_ALIAS);

    @Override
    public Optional<Type> findById(Long id) {
        return Optional.of(entityManager.find(Type.class, id));
    }

    @Override
    public List<Type> findAll() {
        TypedQuery<Type> query = entityManager.createQuery(SELECT_TYPE_STATEMENT, Type.class);
        return query.getResultList();
    }
}
