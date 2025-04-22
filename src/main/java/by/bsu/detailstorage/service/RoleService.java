package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Role;
import by.bsu.detailstorage.repository.RoleRepository;
import org.springframework.stereotype.Service;

import static by.bsu.detailstorage.registry.EntityNameRegistry.ROLE;

@Service
public class RoleService extends AbstractService<Role> {

    public RoleService(RoleRepository entityRepository) {
        super(entityRepository, ROLE);
    }
}
