package by.bsu.detailstorage.dtos.devicedtos;

import by.bsu.detailstorage.dtos.ReadDto;
import by.bsu.detailstorage.dtos.branddtos.BrandReadDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryReadDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceReadDto implements ReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    @Valid
    private BrandReadDto brand;

    private String model;

    private CategoryReadDto category;
}
