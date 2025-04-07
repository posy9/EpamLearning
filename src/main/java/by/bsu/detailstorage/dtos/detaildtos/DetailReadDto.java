package by.bsu.detailstorage.dtos.detaildtos;

import by.bsu.detailstorage.dtos.countrydtos.CountryReadDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceReadDto;
import by.bsu.detailstorage.dtos.typedtos.TypeReadDto;
import lombok.Data;

@Data
public class DetailReadDto {

    private  Long id;

    private String name;

    private TypeReadDto type;

    private DeviceReadDto device;

    private CountryReadDto country;

    private int amount;
}
