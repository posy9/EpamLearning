package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.DataEntity;
import by.bsu.detailstorage.registry.EntityNameRegistry;
import by.bsu.detailstorage.repository.EntityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.Optional;

import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.ENTITIES_NOT_FOUND;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.ENTITY_NOT_FOUND;

@RequiredArgsConstructor
public abstract class AbstractService<T extends DataEntity> {

    private final EntityRepository<T, Long> entityRepository;
    private final EntityNameRegistry EntityName;

    public T findById(long id) {
        Optional<T> foundBrand = entityRepository.findById(id);
        if (foundBrand.isPresent()) {
            return foundBrand.get();
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), EntityName.getEntityName(), id));
        }
    }

    public T createEntity(T entity) {
        fieldsToLowerCase(entity);
        entityRepository.save(entity);
        return entity;
    }

    public T updateEntity(long id, T entity) {
        if (entityRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                    EntityName.getEntityName(), id));
        }
        fieldsToLowerCase(entity);
        entity.setId(id);
        return entityRepository.save(entity);
    }

    public void deleteEntity(long id) {
        Optional<T> entityForDelete = entityRepository.findById(id);
        if (entityForDelete.isPresent()) {
            T entity = entityForDelete.get();
            entityRepository.delete(entity);
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), EntityName.getEntityName(), id));
        }
    }

    public Page<T> findMultiple(Specification<T> specification, Pageable pageable) {
        Page<T> foundEntities = entityRepository.findAll(specification, pageable);
        if (!foundEntities.isEmpty()) {
            return foundEntities;
        } else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }

    @SneakyThrows
    private void fieldsToLowerCase(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(entity);
            if (fieldValue instanceof String) {
                field.set(entity, ((String) fieldValue).trim().toLowerCase());
            }
        }
    }
}
