package by.bsu.detailstorage.dtos.categorydtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryCreateDto {

    @NotBlank(message = "should not be empty")
    private String name;

}
