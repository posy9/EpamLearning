package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.detaildtos.DetailCreateDto;
import by.bsu.detailstorage.dtos.detaildtos.DetailReadDto;
import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.service.DetailService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/details")
public class DetailController extends AbstractController<Detail, DetailReadDto, DetailCreateDto> {

    public DetailController(ModelMapper modelMapper, DetailService service) {
        super(modelMapper, service, Detail.class, DetailReadDto.class);
    }
}
