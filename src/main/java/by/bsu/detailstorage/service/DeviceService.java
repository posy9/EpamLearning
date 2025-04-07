package by.bsu.detailstorage.service;

import by.bsu.detailstorage.exception.IllegalEntityRemovingException;
import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Category;
import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.repository.BrandRepository;
import by.bsu.detailstorage.repository.DeviceRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DEVICE;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
public class DeviceService implements AbstractService<Device> {

    private static final String SPACE = " ";
    private final DeviceRepository deviceRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, BrandRepository brandRepository) {
        this.deviceRepository = deviceRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    public Device createEntity(Device device) {
        Optional<Brand> brand = brandRepository.findById(device.getBrand().getId());
        device.setModel(device.getModel().trim().toLowerCase());
        try {
            return deviceRepository.create(device);
        }
        catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    DEVICE.getEntityName(), brand.get().getName() + SPACE + device.getModel()));
        }
    }

    @Override
    public Device updateEntity(long id, Device device) {
        if (deviceRepository.findById(id).isPresent()) {
            device.setId(id);
            return deviceRepository.update(device);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }

    @Override
    public void deleteEntity(long id) {
        Optional<Device> deviceForDelete = deviceRepository.findById(id);
        if (deviceForDelete.isPresent()) {
            Device device = deviceForDelete.get();
            if(!hasDependencies(device)) {
                deviceRepository.delete(device);
            } else {
                throw new IllegalEntityRemovingException(String.format(ENTITY_WITH_DEPENDENCIES
                        .getMessage(), device.getBrand().getName() + SPACE + device.getModel(), device.getId()));
            }
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }

    public List<Device> findMultipleDevices(Pageable pageable) {
        List<Device> foundDevices = deviceRepository.readMultiple(pageable);
        if(!foundDevices.isEmpty()) {
            return foundDevices;
        }
        else throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
    }

    public Device findById(long id) {
        Optional<Device> foundedDevice = deviceRepository.findById(id);
        if (foundedDevice.isPresent()) {
            return foundedDevice.get();
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }


    private boolean hasDependencies(Device device) {
        return !device.getDetails().isEmpty();
    }

}
