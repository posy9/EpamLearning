package by.bsu.detailstorage.dtos.detaildtos;

import by.bsu.detailstorage.dtos.CreateDto;
import by.bsu.detailstorage.dtos.countrydtos.CountryReadDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceReadDto;
import by.bsu.detailstorage.dtos.typedtos.TypeReadDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class DetailCreateDto implements CreateDto {

    @NotBlank(message = "should not be empty")
    private String name;

    @Valid
    @NotNull(message = "should not be null")
    private TypeReadDto type;

    @Valid
    @NotNull(message = "should not be null")
    private DeviceReadDto device;

    @Valid
    @NotNull(message = "should not be null")
    private CountryReadDto country;

    @PositiveOrZero(message = "should be non-negative")
    private int amount;
}
