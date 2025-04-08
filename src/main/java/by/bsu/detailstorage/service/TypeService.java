package by.bsu.detailstorage.service;

import by.bsu.detailstorage.exception.IllegalEntityRemoveException;
import by.bsu.detailstorage.model.Type;
import by.bsu.detailstorage.repository.TypeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.COUNTRY;
import static by.bsu.detailstorage.registry.EntityNameRegistry.TYPE;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService implements AbstractService<Type> {

    private final TypeRepository typeRepository;

    @Override
    public Type findById(long id) {
        Optional<Type> foundType = typeRepository.findById(id);
        if (foundType.isPresent()) {
            return foundType.get();
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), TYPE.getEntityName(), id));
        }
    }

    @Override
    public Type createEntity(Type type) {
        type.setName(type.getName().trim().toLowerCase());
        try {
            typeRepository.create(type);
            return type;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    TYPE.getEntityName(), type.getName()));
        }
    }

    @Override
    public Type updateEntity(long id, Type type) {
        if(typeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                    TYPE.getEntityName(), id));
        }
        type.setName(type.getName().trim().toLowerCase());
        if (typeRepository.findByName(type.getName()).isEmpty()) {
            type.setId(id);
            return typeRepository.update(type);
        } else {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    TYPE.getEntityName(), type.getName()));
        }
    }

    @Override
    public void deleteEntity(long id) {
        Optional<Type> typeForDelete = typeRepository.findById(id);
        if (typeForDelete.isPresent()) {
            Type type = typeForDelete.get();
            if (!hasDependencies(type)) {
                typeRepository.delete(type);
            } else {
                throw new IllegalEntityRemoveException(String.format(ENTITY_WITH_DEPENDENCIES
                        .getMessage(), type.getName(), type.getId()));
            }
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), TYPE.getEntityName(), id));
        }
    }

    @Override
    public List<Type> findMultiple(Pageable pageable) {
        List<Type> foundTypes = typeRepository.readMultiple(pageable);
        if (!foundTypes.isEmpty()) {
            return foundTypes;
        } else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }

    private boolean hasDependencies(Type type) {
        return !type.getDetails().isEmpty();
    }
}
