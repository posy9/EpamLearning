package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.branddtos.BrandFilterDto;
import by.bsu.detailstorage.model.Brand;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BrandSpecificationBuilder implements SpecificationBuilder<Brand, BrandFilterDto> {

    @Override
    public Specification<Brand> build(BrandFilterDto filterDto) {
        return null;
    }
}
