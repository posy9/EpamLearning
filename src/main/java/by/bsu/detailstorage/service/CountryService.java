package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Country;
import by.bsu.detailstorage.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.COUNTRY;

@Service
@Transactional
public class CountryService extends AbstractService<Country> {

    public CountryService(CountryRepository repository) {
        super(repository, COUNTRY);
    }
}
