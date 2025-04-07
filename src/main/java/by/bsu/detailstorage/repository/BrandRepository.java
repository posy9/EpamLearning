package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Brand;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public final class BrandRepository extends CommonRepository<Brand> {

    @Override
    public Optional<Brand> findById(Long id) {
        return Optional.of(entityManager.find(Brand.class, id));
    }

    @Override
    public List<Brand> readMultiple(Pageable pageable) {
        return List.of();
    }

}
