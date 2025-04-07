package by.bsu.detailstorage.service;

import by.bsu.detailstorage.exception.IllegalEntityRemovingException;
import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.repository.BrandRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.BRAND;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
public class BrandService implements AbstractUtilityEntitiesService<Brand> {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand findById(long id) {
        Optional<Brand> foundBrand = brandRepository.findById(id);
        if (foundBrand.isPresent()) {
            return foundBrand.get();
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), BRAND.getEntityName(), id));
        }
    }

    @Override
    public Brand createEntity(Brand brand) {
        brand.setName(brand.getName().trim().toLowerCase());
        try {
            brandRepository.create(brand);
            return brand;
        } catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    BRAND.getEntityName(), brand.getName()));
        }
    }

    @Override
    public Brand updateEntity(long id, Brand brand) {
        if (brandRepository.findById(id).isPresent()) {
            brand.setId(id);
            return brandRepository.update(brand);
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(),
                    BRAND.getEntityName(), id));
        }
    }

    @Override
    public void deleteEntity(long id) {
        Optional<Brand> brandForDelete = brandRepository.findById(id);
        if (brandForDelete.isPresent()) {
            Brand brand = brandForDelete.get();
            if (!hasDependencies(brand)) {
                brandRepository.delete(brand);
            } else {
                throw new IllegalEntityRemovingException(String.format(ENTITY_WITH_DEPENDENCIES
                        .getMessage(), brand.getName(), brand.getId()));
            }
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), BRAND.getEntityName(), id));
        }
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> foundBrands = brandRepository.findAll();
        if (!foundBrands.isEmpty()) {
            return foundBrands;
        } else {
            throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
        }
    }

    private boolean hasDependencies(Brand brand) {
        return !brand.getDevices().isEmpty();
    }

}
