package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.countrydtos.CountryCreateDto;
import by.bsu.detailstorage.dtos.countrydtos.CountryReadDto;
import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final ModelMapper modelMapper;

    @GetMapping
    List<CountryReadDto> getAllCountries() {
        List<Country> countries = countryService.findAll();
        return countries.stream()
                .map(country -> modelMapper.map(country, CountryReadDto.class))
                .toList();
    }

    @GetMapping(value = "/{id}")
    CountryReadDto getCountryById(@PathVariable long id) {
        Country country = countryService.findById(id);
        return modelMapper.map(country, CountryReadDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryReadDto createCountry(@Valid @RequestBody CountryCreateDto countryCreateDto) {
        Country country = modelMapper.map(countryCreateDto, Country.class);
        countryService.createEntity(country);
        Country createdCountry = countryService.findById(country.getId());
        return modelMapper.map(createdCountry, CountryReadDto.class);
    }

    @PutMapping(value = "/{id}")
    CountryReadDto updateCountry(@PathVariable long id, @RequestBody CountryCreateDto countryCreateDto) {
        countryService.updateEntity(id, modelMapper.map(countryCreateDto, Country.class));
        Country updatedCountry = countryService.findById(id);
        return modelMapper.map(updatedCountry, CountryReadDto.class);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteCountry(@PathVariable long id) {
        countryService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
