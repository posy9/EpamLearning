package by.bsu.detailstorage.service;

import by.bsu.detailstorage.exception.IllegalEntityRemovingException;
import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.repository.CategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.CATEGORY;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
public class CategoryService implements AbstractUtilityEntitiesService<Category> {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(long id) {
        Optional<Category> foundCategory = categoryRepository.findById(id);
        if (foundCategory.isPresent()) {
            return foundCategory.get();
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), CATEGORY.getEntityName(), id));
        }
    }

    @Override
    public Category createEntity(Category category) {
        category.setName(category.getName().trim().toLowerCase());
        try {
            categoryRepository.create(category);
            return category;
        }
        catch (ConstraintViolationException e){
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    CATEGORY.getEntityName(), category.getName()));
        }
    }

    @Override
    public Category updateEntity(long id, Category category) {
            if (categoryRepository.findById(id).isPresent()) {
                category.setId(id);
                return categoryRepository.update(category);
            } else {
                throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                        CATEGORY.getEntityName(), id));
            }
    }

    @Override
    public void deleteEntity(long id) {
        Optional<Category> categoryForDelete = categoryRepository.findById(id);
        if (categoryForDelete.isPresent()) {
             Category category = categoryForDelete.get();
            if(!hasDependencies(category)){
                categoryRepository.delete(category);
            } else {
                throw new IllegalEntityRemovingException(String.format(ENTITY_WITH_DEPENDENCIES
                        .getMessage(),category.getName(),category.getId()));
            }
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), CATEGORY.getEntityName(), id));
        }
    }

    @Override
    public List<Category> findAll() {
        List<Category> foundCategories = categoryRepository.findAll();
        if (!foundCategories.isEmpty()) {
            return foundCategories;
        }
        else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }

    private boolean hasDependencies(Category category) {
        return !category.getDevices().isEmpty();
    }

}
