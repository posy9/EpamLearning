package by.bsu.detailstorage.dtos.countrydtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CountryReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    private String name;

}
