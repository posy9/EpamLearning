package by.bsu.detailstorage.dtos.devicedtos;

import by.bsu.detailstorage.dtos.branddtos.BrandUpdateDto;
import by.bsu.detailstorage.dtos.categorydtos.CategoryUpdateDto;
import lombok.Data;

@Data
public class DeviceUpdateDto {

    private BrandUpdateDto brand;

    private String model;

    private CategoryUpdateDto category;

}
