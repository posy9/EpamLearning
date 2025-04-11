package by.bsu.detailstorage.dtos.typedtos;

import by.bsu.detailstorage.dtos.CreateDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TypeCreateDto implements CreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
