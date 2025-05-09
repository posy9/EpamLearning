package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.categorydtos.CategoryFilterDto;
import by.bsu.detailstorage.model.Category;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpecificationBuilder implements SpecificationBuilder<Category, CategoryFilterDto> {

    @Override
    public Specification<Category> build(CategoryFilterDto filterDto) {
        return null;
    }
}
