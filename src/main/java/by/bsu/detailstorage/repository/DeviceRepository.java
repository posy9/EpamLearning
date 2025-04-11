package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {


    long countByBrandId(long brandId);

    long countByCategoryId(long categoryId);

    boolean existsByBrandIdAndModel(long brandId, String model);
}
