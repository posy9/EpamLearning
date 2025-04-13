package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.branddtos.BrandCreateDto;
import by.bsu.detailstorage.dtos.branddtos.BrandFilterDto;
import by.bsu.detailstorage.dtos.branddtos.BrandReadDto;
import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.service.BrandService;
import by.bsu.detailstorage.specification.BrandSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brands")
public class BrandController extends AbstractController<Brand, BrandReadDto, BrandCreateDto, BrandFilterDto> {

    public BrandController(ModelMapper modelMapper, BrandService service, BrandSpecificationBuilder brandSpecificationBuilder) {
        super(modelMapper, service, Brand.class, BrandReadDto.class, brandSpecificationBuilder);
    }
}
