package by.bsu.detailstorage.dtos.devicedtos;

import by.bsu.detailstorage.dtos.branddtos.BrandCreateDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryCreateDto;
import lombok.Data;

@Data
public class DeviceCreateDto {

    private BrandCreateDto brand;

    private String model;

    private CategoryCreateDto category;

}
