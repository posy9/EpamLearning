package by.bsu.detailstorage.dtos.countrydtos;

import by.bsu.detailstorage.dtos.ReadDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CountryReadDto implements ReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    private String name;
}
