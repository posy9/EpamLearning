package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.typedtos.TypeCreateDto;
import by.bsu.detailstorage.dtos.typedtos.TypeFilterDto;
import by.bsu.detailstorage.dtos.typedtos.TypeReadDto;
import by.bsu.detailstorage.model.Type;
import by.bsu.detailstorage.service.TypeService;
import by.bsu.detailstorage.specification.TypeSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/types")
public class TypeController extends AbstractController<Type, TypeReadDto, TypeCreateDto, TypeFilterDto> {

    public TypeController(ModelMapper modelMapper, TypeService service, TypeSpecificationBuilder typeSpecificationBuilder) {
        super(modelMapper, service, Type.class, TypeReadDto.class, typeSpecificationBuilder);
    }
}

