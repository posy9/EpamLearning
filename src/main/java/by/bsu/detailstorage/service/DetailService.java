package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.repository.DetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DETAIL;

@Service
@Transactional
public class DetailService extends AbstractService<Detail> {

    public DetailService(DetailRepository repository) {
        super(repository, DETAIL);
    }
}
