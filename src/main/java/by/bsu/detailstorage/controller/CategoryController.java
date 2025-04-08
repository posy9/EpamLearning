package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.categorydtos.CategoryCreateDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryReadDto;
import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @GetMapping
    List<CategoryReadDto> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(category -> modelMapper.map(category, CategoryReadDto.class))
                .toList();
    }

    @GetMapping(value = "/{id}")
    CategoryReadDto getCategoryById(@PathVariable long id) {
        Category category = categoryService.findById(id);
        return modelMapper.map(category, CategoryReadDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryReadDto createCategory(@Valid @RequestBody CategoryCreateDto CategoryCreateDto) {
        Category category = modelMapper.map(CategoryCreateDto, Category.class);
        categoryService.createEntity(category);
        Category createdCategory = categoryService.findById(category.getId());
        return modelMapper.map(createdCategory, CategoryReadDto.class);
    }

    @PutMapping(value = "/{id}")
    CategoryReadDto updateCategory(@PathVariable long id, @RequestBody CategoryCreateDto categoryCreateDto) {
        categoryService.updateEntity(id, modelMapper.map(categoryCreateDto, Category.class));
        Category updatedCategory = categoryService.findById(id);
        return modelMapper.map(updatedCategory, CategoryReadDto.class);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
