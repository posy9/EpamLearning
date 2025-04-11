package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Brand;
import by.bsu.detailstorage.model.Device;
import by.bsu.detailstorage.repository.BrandRepository;
import by.bsu.detailstorage.repository.DetailRepository;
import by.bsu.detailstorage.repository.DeviceRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DEVICE;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DeviceService implements AbstractService<Device> {

    private static final String SPACE = " ";
    private final DeviceRepository deviceRepository;
    private final BrandRepository brandRepository;

    @Override
    public Device createEntity(Device device) {
        Optional<Brand> brand = brandRepository.findById(device.getBrand().getId());
        device.setModel(device.getModel().trim().toLowerCase());
        try {
            return deviceRepository.save(device);
        }
        catch (ConstraintViolationException e) {
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                    DEVICE.getEntityName(), brand.get().getName() + SPACE + device.getModel()));
        }
    }

    @Override
    public Device updateEntity(long id, Device device) {
        if(deviceRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
        device.setModel(device.getModel().trim().toLowerCase());
        device.setId(id);
        return deviceRepository.save(device);
    }

    @Override
    public void deleteEntity(long id) {
        Optional<Device> deviceForDelete = deviceRepository.findById(id);
        if (deviceForDelete.isPresent()) {
            Device device = deviceForDelete.get();
            deviceRepository.delete(device);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DEVICE.getEntityName(), id));
        }
    }

    @Override
    public List<Device> findMultiple(Pageable pageable) {
        Page<Device> foundDevices = deviceRepository.findAll(pageable);
        if(!foundDevices.isEmpty()) {
            return foundDevices.getContent();
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
}
