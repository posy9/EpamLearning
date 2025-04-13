package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Type;
import by.bsu.detailstorage.repository.TypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.BRAND;

@Service
@Transactional
public class TypeService extends AbstractService<Type> {

    public TypeService(TypeRepository repository) {
        super(repository, BRAND);
    }
}
