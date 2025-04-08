package by.bsu.detailstorage.dtos.countrydtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CountryCreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
