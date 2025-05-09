package by.bsu.detailstorage.dtos.branddtos;

import by.bsu.detailstorage.dtos.CreateDto;
import lombok.Data;

@Data
public class BrandCreateDto implements CreateDto {

    private String name;
}
