package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.CATEGORY;

@Service
@Transactional

public class CategoryService extends AbstractService<Category> {

    public CategoryService(CategoryRepository repository) {
        super(repository, CATEGORY);
    }
}
