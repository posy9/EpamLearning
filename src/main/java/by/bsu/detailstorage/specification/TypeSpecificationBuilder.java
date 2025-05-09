package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.typedtos.TypeFilterDto;
import by.bsu.detailstorage.model.Type;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class TypeSpecificationBuilder implements SpecificationBuilder<Type, TypeFilterDto> {

    @Override
    public Specification<Type> build(TypeFilterDto filterDto) {
        return null;
    }
}
