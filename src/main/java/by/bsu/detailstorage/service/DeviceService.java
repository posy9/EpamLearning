package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DEVICE;

@Service
@Transactional
public class DeviceService extends AbstractService<Device> {

    public DeviceService(DeviceRepository repository) {
        super(repository, DEVICE);
    }
}
