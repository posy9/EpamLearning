package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.devicedtos.DeviceCreateDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceReadDto;
import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.service.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController extends AbstractController<Device, DeviceReadDto, DeviceCreateDto> {

    public DeviceController(ModelMapper modelMapper, DeviceService service) {
        super(modelMapper, service, Device.class, DeviceReadDto.class);
    }
}
