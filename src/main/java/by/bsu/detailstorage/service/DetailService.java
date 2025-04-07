package by.bsu.detailstorage.service;

import by.bsu.detailstorage.repository.DetailRepository;
import by.bsu.detailstorage.model.Detail;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DETAIL;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.ENTITY_NOT_FOUND;


@Service
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;


    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public Detail findById(Long id) {
        Detail detail = detailRepository.findById(id);
        if (detail != null) {
            return detailRepository.findById(id);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }
    }


    public Detail createDetail(Detail detail) {
        detail.setName(detail.getName().trim().toLowerCase());
        try {
            detailRepository.create(detail);
            return detail;
        }
        catch (ConstraintViolationException e){
            throw new EntityExistsException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), detail.getName()));
        }
    }

    public Detail updateDetail(long id, Detail detail) {
        if (detailRepository.findById(id) != null) {
            detail.setId(id);
            return detailRepository.update(detail);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }

    }

    public void deleteDetail(long id) {
        Detail detailForDelete = detailRepository.findById(id);
        if (detailForDelete != null) {
            detailRepository.delete(detailForDelete);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }
    }

    public List<Detail> findMultipleDetails(Pageable pageable) {
       return detailRepository.readMultiple(pageable);
    }
}
