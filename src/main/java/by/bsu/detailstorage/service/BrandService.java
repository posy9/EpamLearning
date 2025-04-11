package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.repository.BrandRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.BRAND;

@Service
@Transactional
public class BrandService extends AbstractService<Brand> {

    public BrandService(BrandRepository repository) {
       super(repository, BRAND);
    }
}
