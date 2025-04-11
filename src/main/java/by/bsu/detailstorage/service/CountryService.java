package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.repository.CountryRepository;
import by.bsu.detailstorage.repository.DetailRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.COUNTRY;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService implements AbstractService<Country> {

    private final CountryRepository countryRepository;

    @Override
    public Country findById(long id) {
        Optional<Country> foundCountry = countryRepository.findById(id);
        if (foundCountry.isPresent()) {
            return foundCountry.get();
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), COUNTRY.getEntityName(), id));
        }
    }

    @Override
    public Country createEntity(Country country) {
        country.setName(country.getName().trim().toLowerCase());
        try {
            countryRepository.save(country);
            return country;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    COUNTRY.getEntityName(), country.getName()));
        }
    }

    @Override
    public Country updateEntity(long id, Country country) {
        if(countryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                    COUNTRY.getEntityName(), id));
        }
        country.setName(country.getName().trim().toLowerCase());
        country.setId(id);
        return countryRepository.save(country);

    }

    @Override
    public void deleteEntity(long id) {
        Optional<Country> countryForDelete = countryRepository.findById(id);
        if (countryForDelete.isPresent()) {
            Country country = countryForDelete.get();
            countryRepository.delete(country);
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), COUNTRY.getEntityName(), id));
        }
    }

    @Override
    public List<Country> findMultiple(Pageable pageable) {
        Page<Country> foundCountries = countryRepository.findAll(pageable);
        if (!foundCountries.isEmpty()) {
            return foundCountries.getContent();
        } else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }
}
