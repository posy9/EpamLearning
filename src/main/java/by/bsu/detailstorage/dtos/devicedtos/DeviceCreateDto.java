package by.bsu.detailstorage.dtos.devicedtos;

import by.bsu.detailstorage.dtos.branddtos.BrandReadDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryReadDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceCreateDto {

    @Valid
    @NotNull(message = "should not be null")
    private BrandReadDto brand;

    @NotBlank(message = "should not be empty")
    private String model;

    @NotNull(message = "should not be null")
    private CategoryReadDto category;

}
