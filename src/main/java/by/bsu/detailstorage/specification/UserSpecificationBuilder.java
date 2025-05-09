package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.userdtos.UserFilterDto;
import by.bsu.detailstorage.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationBuilder implements SpecificationBuilder<User, UserFilterDto> {

    @Override
    public Specification<User> build(UserFilterDto filterDto) {
        return null;
    }
}
