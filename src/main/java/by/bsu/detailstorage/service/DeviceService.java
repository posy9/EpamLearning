package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.repository.BrandRepository;
import by.bsu.detailstorage.repository.DeviceRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DEVICE;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.ENTITY_EXISTS;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.ENTITY_NOT_FOUND;

@Service
@Transactional
public class DeviceService {

    private static final String SPACE = " ";
    private final DeviceRepository deviceRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, BrandRepository brandRepository) {
        this.deviceRepository = deviceRepository;
        this.brandRepository = brandRepository;
    }

    public List<Device> findMultipleDevices(Pageable pageable) {
        return deviceRepository.readMultiple(pageable);
    }

    public Device findById(long id) {
        Device device = deviceRepository.findById(id);
        if (device != null) {
            return deviceRepository.findById(id);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }

    public Device createDevice(Device device) {
        Brand brand = brandRepository.findById(device.getBrand().getId());
        device.setModel(device.getModel().trim().toLowerCase());
        try {
            return deviceRepository.create(device);
        }
        catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    DEVICE.getEntityName(), brand.getName() + SPACE + device.getModel()));
        }
    }

    public Device updateDevice(long id, Device device) {
        if (deviceRepository.findById(id) != null) {
            device.setId(id);
            return deviceRepository.update(device);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }

    public void deleteDevice(long id) {
        Device deviceForDelete = deviceRepository.findById(id);
        if (deviceForDelete != null) {
            deviceRepository.delete(deviceForDelete);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }

}
