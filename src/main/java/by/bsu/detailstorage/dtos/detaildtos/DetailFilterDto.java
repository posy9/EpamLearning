package by.bsu.detailstorage.dtos.detaildtos;

import by.bsu.detailstorage.dtos.FilterDto;
import lombok.Data;

@Data
public class DetailFilterDto implements FilterDto {

    private Long type_id;

    private Long device_id;
}
