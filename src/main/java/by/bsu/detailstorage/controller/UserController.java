package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.typedtos.TypeReadDto;
import by.bsu.detailstorage.dtos.userdtos.UserCreateDto;
import by.bsu.detailstorage.dtos.userdtos.UserFilterDto;
import by.bsu.detailstorage.dtos.userdtos.UserReadDto;
import by.bsu.detailstorage.model.Type;
import by.bsu.detailstorage.model.User;
import by.bsu.detailstorage.service.TypeService;
import by.bsu.detailstorage.service.UserService;
import by.bsu.detailstorage.specification.TypeSpecificationBuilder;
import by.bsu.detailstorage.specification.UserSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User, UserReadDto, UserCreateDto, UserFilterDto> {

    public UserController(ModelMapper modelMapper, UserService service, UserSpecificationBuilder userSpecificationBuilder) {
        super(modelMapper, service, User.class, UserReadDto.class, userSpecificationBuilder);
    }
}
