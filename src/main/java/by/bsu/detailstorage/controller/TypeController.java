package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.typedtos.TypeCreateDto;
import by.bsu.detailstorage.dtos.typedtos.TypeReadDto;
import by.bsu.detailstorage.model.Type;
import by.bsu.detailstorage.service.TypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class TypeController {
    private final TypeService typeService;
    private final ModelMapper modelMapper;

    @GetMapping
    List<TypeReadDto> getAllTypes() {
        List<Type> types = typeService.findAll();
        return types.stream()
                .map(type -> modelMapper.map(type, TypeReadDto.class))
                .toList();
    }

    @GetMapping(value = "/{id}")
    TypeReadDto getTypeById(@PathVariable long id) {
        Type type = typeService.findById(id);
        return modelMapper.map(type, TypeReadDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TypeReadDto createType(@Valid @RequestBody TypeCreateDto typeCreateDto) {
        Type type = modelMapper.map(typeCreateDto, Type.class);
        typeService.createEntity(type);
        Type createdType = typeService.findById(type.getId());
        return modelMapper.map(createdType, TypeReadDto.class);
    }

    @PutMapping(value = "/{id}")
    TypeReadDto updateType(@PathVariable long id, @RequestBody TypeCreateDto typeCreateDto) {
        typeService.updateEntity(id, modelMapper.map(typeCreateDto, Type.class));
        Type updatedType = typeService.findById(id);
        return modelMapper.map(updatedType, TypeReadDto.class);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteType(@PathVariable long id) {
        typeService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}

