package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.roledtos.RoleCreateDto;
import by.bsu.detailstorage.dtos.roledtos.RoleFilterDto;
import by.bsu.detailstorage.dtos.roledtos.RoleReadDto;
import by.bsu.detailstorage.model.Role;
import by.bsu.detailstorage.service.RoleService;
import by.bsu.detailstorage.specification.RoleSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends AbstractController<Role, RoleReadDto, RoleCreateDto, RoleFilterDto> {

    public RoleController(ModelMapper modelMapper, RoleService service, RoleSpecificationBuilder roleSpecificationBuilder) {
        super(modelMapper, service, Role.class, RoleReadDto.class, roleSpecificationBuilder);
    }
}
