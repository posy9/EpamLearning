package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.categorydtos.CategoryCreateDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryFilterDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryReadDto;
import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.service.CategoryService;
import by.bsu.detailstorage.specification.CategorySpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractController<Category, CategoryReadDto, CategoryCreateDto, CategoryFilterDto> {

    public CategoryController(ModelMapper modelMapper, CategoryService service, CategorySpecificationBuilder categorySpecificationBuilder) {
        super(modelMapper, service, Category.class, CategoryReadDto.class, categorySpecificationBuilder);
    }
}
