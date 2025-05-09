package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.detaildtos.DetailCreateDto;
import by.bsu.detailstorage.dtos.detaildtos.DetailFilterDto;
import by.bsu.detailstorage.dtos.detaildtos.DetailReadDto;
import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.service.DetailService;
import by.bsu.detailstorage.specification.DetailSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/details")
public class DetailController extends AbstractController<Detail, DetailReadDto, DetailCreateDto, DetailFilterDto> {

    public DetailController(ModelMapper modelMapper, DetailService service, DetailSpecificationBuilder detailSpecificationBuilder) {
        super(modelMapper, service, Detail.class, DetailReadDto.class, detailSpecificationBuilder);
    }
}
