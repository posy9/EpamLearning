package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.devicedtos.DeviceCreateDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceFilterDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceReadDto;
import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.service.DeviceService;
import by.bsu.detailstorage.specification.DeviceSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController extends AbstractController<Device, DeviceReadDto, DeviceCreateDto, DeviceFilterDto> {

    public DeviceController(ModelMapper modelMapper, DeviceService service, DeviceSpecificationBuilder deviceSpecificationBuilder) {
        super(modelMapper, service, Device.class, DeviceReadDto.class, deviceSpecificationBuilder);
    }
}
