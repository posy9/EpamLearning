package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.roledtos.RoleFilterDto;
import by.bsu.detailstorage.model.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class RoleSpecificationBuilder implements SpecificationBuilder<Role, RoleFilterDto> {

    @Override
    public Specification<Role> build(RoleFilterDto filterDto) {
        return null;
    }
}
