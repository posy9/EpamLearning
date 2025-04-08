package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.branddtos.BrandCreateDto;
import by.bsu.detailstorage.dtos.branddtos.BrandReadDto;
import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Slf4j
public class BrandController {
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    @GetMapping
    List<BrandReadDto> getAllBrands(Pageable pageable) {
        List<Brand> brands = brandService.findMultiple(pageable);
        return brands.stream()
                .map(brand -> modelMapper.map(brand, BrandReadDto.class))
                .toList();
    }

    @GetMapping(value = "/{id}")
    BrandReadDto getBrandById(@PathVariable long id) {
        Brand brand = brandService.findById(id);
        return modelMapper.map(brand, BrandReadDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BrandReadDto createBrand(@Valid @RequestBody BrandCreateDto brandCreateDto) {
        Brand brand = modelMapper.map(brandCreateDto, Brand.class);
        brandService.createEntity(brand);
        Brand createdBrand = brandService.findById(brand.getId());
        return modelMapper.map(createdBrand, BrandReadDto.class);
    }

    @PutMapping(value = "/{id}")
    BrandReadDto updateBrand(@PathVariable long id, @RequestBody BrandCreateDto brandCreateDto) {
        brandService.updateEntity(id, modelMapper.map(brandCreateDto, Brand.class));
        Brand updatedBrand = brandService.findById(id);
        return modelMapper.map(updatedBrand, BrandReadDto.class);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteBrand(@PathVariable long id) {
        brandService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
