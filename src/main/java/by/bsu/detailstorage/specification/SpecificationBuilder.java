package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.FilterDto;
import by.bsu.detailstorage.model.DataEntity;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T extends DataEntity, DTO extends FilterDto> {

    Specification<T> build(DTO filterDto);
}
