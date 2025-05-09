package by.bsu.detailstorage.dtos.categorydtos;

import by.bsu.detailstorage.dtos.ReadDto;
import lombok.Data;

@Data
public class CategoryReadDto implements ReadDto {

    private Long id;

    private String name;
}
