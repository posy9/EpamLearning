package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.countrydtos.CountryCreateDto;
import by.bsu.detailstorage.dtos.countrydtos.CountryReadDto;
import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/countries")
public class CountryController extends AbstractController<Country, CountryReadDto, CountryCreateDto> {

    public CountryController(ModelMapper modelMapper, CountryService service) {
        super(modelMapper, service, Country.class, CountryReadDto.class);
    }
}
