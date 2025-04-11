package by.bsu.detailstorage.repository;

import by.bsu.detailstorage.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    boolean existsByName(String name);
}
