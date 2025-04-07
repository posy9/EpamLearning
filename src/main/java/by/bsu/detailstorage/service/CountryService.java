package by.bsu.detailstorage.service;

import by.bsu.detailstorage.exception.IllegalEntityRemoveException;
import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.repository.CountryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.COUNTRY;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
@RequiredArgsConstructor
public class CountryService implements AbstractUtilityEntitiesService<Country> {

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
            countryRepository.create(country);
            return country;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    COUNTRY.getEntityName(), country.getName()));
        }
    }

    @Override
    public Country updateEntity(long id, Country country) {
        if (countryRepository.findById(id).isPresent()) {
            country.setId(id);
            return countryRepository.update(country);
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                    COUNTRY.getEntityName(), id));
        }
    }

    @Override
    public void deleteEntity(long id) {
        Optional<Country> countryForDelete = countryRepository.findById(id);
        if (countryForDelete.isPresent()) {
            Country country = countryForDelete.get();
            if (!hasDependencies(country)) {
                countryRepository.delete(country);
            } else {
                throw new IllegalEntityRemoveException(String.format(ENTITY_WITH_DEPENDENCIES
                        .getMessage(), country.getName(), country.getId()));
            }
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), COUNTRY.getEntityName(), id));
        }
    }

    @Override
    public List<Country> findAll() {
        List<Country> foundCountries = countryRepository.findAll();
        if (!foundCountries.isEmpty()) {
            return foundCountries;
        } else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }

    private boolean hasDependencies(Country country) {
        return !country.getDetails().isEmpty();
    }
}
