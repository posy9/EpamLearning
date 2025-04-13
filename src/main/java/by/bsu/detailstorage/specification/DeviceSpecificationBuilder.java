package by.bsu.detailstorage.specification;

import by.bsu.detailstorage.dtos.devicedtos.DeviceFilterDto;
import by.bsu.detailstorage.model.Device;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DeviceSpecificationBuilder implements SpecificationBuilder<Device, DeviceFilterDto> {

    @Override
    public Specification<Device> build(DeviceFilterDto filterDto) {
        return null;
    }
}
