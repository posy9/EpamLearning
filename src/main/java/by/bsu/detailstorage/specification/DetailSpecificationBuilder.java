package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.detaildtos.DetailFilterDto;
import by.bsu.detailstorage.model.Detail;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DetailSpecificationBuilder implements SpecificationBuilder<Detail, DetailFilterDto> {

    @Override
    public Specification<Detail> build(DetailFilterDto filterDto) {
        return withDeviceId(filterDto.getDevice_id())
                .and(withTypeId(filterDto.getType_id())
                .and(withCountryId(filterDto.getCountry_id()))
                .and(withNameLike(filterDto.getName())));
    };

    private Specification<Detail> withDeviceId(Long deviceId) {
        return ((root, query, criteriaBuilder) -> deviceId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("device").get("id"), deviceId));
    }

    private Specification<Detail> withTypeId(Long typeId) {
        return ((root, query, criteriaBuilder) -> typeId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("type").get("id"), typeId));
    }

    private Specification<Detail> withCountryId(Long countryId) {
        return ((root, query, criteriaBuilder) -> countryId == null
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("country").get("id"), countryId));
    }

    private Specification<Detail> withNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                (name == null || name.isEmpty())
                        ? criteriaBuilder.conjunction()
                        : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }


}
