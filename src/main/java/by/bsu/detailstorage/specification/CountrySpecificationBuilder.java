package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.countrydtos.CountryFilterDto;
import by.bsu.detailstorage.model.Country;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CountrySpecificationBuilder implements SpecificationBuilder<Country, CountryFilterDto> {

    @Override
    public Specification<Country> build(CountryFilterDto filterDto) {
        return null;
    }
}
