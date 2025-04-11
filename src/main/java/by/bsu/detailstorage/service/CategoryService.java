package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.repository.CategoryRepository;
import by.bsu.detailstorage.repository.DeviceRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.CATEGORY;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService implements AbstractService<Category> {

    private final CategoryRepository categoryRepository;
    private final DeviceRepository deviceRepository;

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
            categoryRepository.save(category);
            return category;
        }
        catch (ConstraintViolationException e){
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    CATEGORY.getEntityName(), category.getName()));
        }
    }

    @Override
    public Category updateEntity(long id, Category category) {
        if(categoryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                    CATEGORY.getEntityName(), id));
        }
        category.setName(category.getName().trim().toLowerCase());
        category.setId(id);
        return categoryRepository.save(category);

    }

    @Override
    public void deleteEntity(long id) {
        Optional<Category> categoryForDelete = categoryRepository.findById(id);
        if (categoryForDelete.isPresent()) {
             Category category = categoryForDelete.get();
             categoryRepository.delete(category);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), CATEGORY.getEntityName(), id));
        }
    }

    @Override
    public List<Category> findMultiple(Pageable pageable) {
        Page<Category> foundCategories = categoryRepository.findAll(pageable);
        if (!foundCategories.isEmpty()) {
            return foundCategories.getContent();
        }
        else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }
}
