package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail, Long> {

    boolean existsByName(String name);

    long countByCountryId(long countryId);

    long countByDeviceId(long deviceId);

    long countByTypeId(long typeId);
}
