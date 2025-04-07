package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.devicedtos.DeviceCreateDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceForListDto;
import by.bsu.detailstorage.dtos.devicedtos.DeviceReadDto;
import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;
    private final ModelMapper modelMapper;

    @GetMapping
    List<DeviceForListDto> getAllDevices(Pageable pageable) {
        List<Device> devices = deviceService.findMultipleDevices(pageable);
        return devices.stream()
                .map(device -> modelMapper.map(device, DeviceForListDto.class))
                .toList();
    }

    @GetMapping(value = "/{id}")
    DeviceReadDto getDeviceById(@PathVariable long id) {
        Device device = deviceService.findById(id);
        return modelMapper.map(device, DeviceReadDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceReadDto createDevice(@Valid @RequestBody DeviceCreateDto deviceCreateDto) {
        Device device = modelMapper.map(deviceCreateDto, Device.class);
        deviceService.createEntity(device);
        Device createdDevice = deviceService.findById(device.getId());
        return modelMapper.map(createdDevice, DeviceReadDto.class);
    }

    @PutMapping(value = "/{id}")
    DeviceReadDto updateDevice(@PathVariable long id, @RequestBody DeviceCreateDto deviceCreateDto) {
        deviceService.updateEntity(id, modelMapper.map(deviceCreateDto, Device.class));
        Device updatedDevice = deviceService.findById(id);
        return modelMapper.map(updatedDevice, DeviceReadDto.class);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteDevice(@PathVariable long id) {
        deviceService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
