package by.bsu.detailstorage.service;

import by.bsu.detailstorage.repository.DetailRepository;
import by.bsu.detailstorage.model.Detail;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static by.bsu.detailstorage.registry.EntityNameRegistry.DETAIL;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;


@Service
@Transactional
public class DetailService implements AbstractService<Detail> {

    private final DetailRepository detailRepository;


    @Autowired
    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @Override
    public Detail findById(long id) {
        Optional<Detail> foundDetail = detailRepository.findById(id);
        if (foundDetail.isPresent()) {
            return foundDetail.get();
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }
    }

    @Override
    public Detail createEntity(Detail detail) {
        detail.setName(detail.getName().trim().toLowerCase());
        try {
            detailRepository.create(detail);
            return detail;
        }
        catch (ConstraintViolationException e){
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(), DETAIL.getEntityName(), detail.getName()));
        }
    }

    @Override
    public Detail updateEntity(long id, Detail detail) {
        if (detailRepository.findById(id).isPresent()) {
            detail.setId(id);
            try {
                return detailRepository.update(detail);
            }
            catch (DataIntegrityViolationException e){
                throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(),
                        DETAIL.getEntityName(), detail.getName()));
            }

        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }

    }

    @Override
    public void deleteEntity(long id) {
        Optional<Detail> detailForDelete = detailRepository.findById(id);
        if (detailForDelete.isPresent()) {
            detailRepository.delete(detailForDelete.get());
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }
    }

    public List<Detail> findMultipleDetails(Pageable pageable) {
        List<Detail> foundDetails = detailRepository.readMultiple(pageable);
        if(!foundDetails.isEmpty()) {
            return foundDetails;
        }
        else throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
    }
}
