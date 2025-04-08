package by.bsu.detailstorage.dtos.typedtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TypeCreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
