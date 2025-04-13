package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.detaildtos.DetailFilterDto;
import by.bsu.detailstorage.model.Detail;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DetailSpecificationBuilder implements SpecificationBuilder<Detail, DetailFilterDto> {

    @Override
    public Specification<Detail> build(DetailFilterDto filterDto) {
        return withDeviceId(filterDto.getDevice_id()).and(withTypeId(filterDto.getType_id()));
    };

    private Specification<Detail> withDeviceId(Long deviceId) {
        return ((root, query, criteriaBuilder) -> deviceId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("device").get("id"), deviceId));
    }

    private Specification<Detail> withTypeId(Long typeId) {
        return ((root, query, criteriaBuilder) -> typeId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("type").get("id"), typeId));
    }
}
