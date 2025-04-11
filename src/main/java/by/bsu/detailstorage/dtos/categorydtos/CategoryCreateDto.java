package by.bsu.detailstorage.dtos.categorydtos;

import by.bsu.detailstorage.dtos.CreateDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreateDto implements CreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
