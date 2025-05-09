package by.bsu.detailstorage.dtos.branddtos;

import by.bsu.detailstorage.dtos.ReadDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BrandReadDto implements ReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    private String name;
}
