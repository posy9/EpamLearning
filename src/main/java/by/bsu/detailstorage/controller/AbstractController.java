package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.CreateDto;
import by.bsu.detailstorage.dtos.FilterDto;
import by.bsu.detailstorage.dtos.ReadDto;
import by.bsu.detailstorage.model.DataEntity;
import by.bsu.detailstorage.service.AbstractService;
import by.bsu.detailstorage.specification.SpecificationBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
public class AbstractController<T extends DataEntity,R extends ReadDto, C extends CreateDto, F extends FilterDto> {

    private final ModelMapper modelMapper;
    private final AbstractService<T> entityService;
    private final Class<T> entityClass;
    private final Class<R> readDtoClass;
    private final SpecificationBuilder<T,F> entitySpecificationBuilder;


    @GetMapping
    Page<R> getAllEntities(@ModelAttribute F filterDto, Pageable pageable) {
        Specification<T> entitySpecification = entitySpecificationBuilder.build(filterDto);
        Page<T> entities = entityService.findMultiple(entitySpecification, pageable);
        return entities.map(entity -> modelMapper.map(entity, readDtoClass));
    }

    @GetMapping(value = "/{id}")
    R getEntityById(@PathVariable long id) {
        T entity = entityService.findById(id);
        return modelMapper.map(entity, readDtoClass);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R createEntity(@Valid @RequestBody C entityCreateDto) {
        T entity = modelMapper.map(entityCreateDto, entityClass);
        entityService.createEntity(entity);
        T createdEntity = entityService.findById(entity.getId());
        return modelMapper.map(createdEntity, readDtoClass);
    }

    @PutMapping(value = "/{id}")
    R updateEntity(@PathVariable long id, @RequestBody C entityCreateDto) {
        entityService.updateEntity(id, modelMapper.map(entityCreateDto, entityClass));
        T updatedEntity = entityService.findById(id);
        return modelMapper.map(updatedEntity, readDtoClass);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteEntity(@PathVariable long id) {
        entityService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
