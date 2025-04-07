package by.bsu.detailstorage.dtos.devicedtos;

import by.bsu.detailstorage.dtos.branddtos.BrandReadDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryReadDto;
import lombok.Data;

@Data
public class DeviceForListDto {

    private Long id;

    private BrandReadDto brand;

    private String model;

    private CategoryReadDto category;
}
