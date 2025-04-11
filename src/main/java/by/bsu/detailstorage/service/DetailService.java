package by.bsu.detailstorage.service;

import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.repository.DetailRepository;
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

import static by.bsu.detailstorage.registry.EntityNameRegistry.DETAIL;
import static by.bsu.detailstorage.registry.ErrorMessagesRegistry.*;

@Service
@Transactional
@RequiredArgsConstructor
public class DetailService implements AbstractService<Detail> {

    private final DetailRepository detailRepository;

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
            detailRepository.save(detail);
            return detail;
        }
        catch (ConstraintViolationException e){
            throw new EntityExistsException(String.format(ENTITY_EXISTS.getMessage(), DETAIL.getEntityName(), detail.getName()));
        }
    }

    @Override
    public Detail updateEntity(long id, Detail detail) {
        if(detailRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND.getMessage(), DETAIL.getEntityName(), id));
        }
        detail.setName(detail.getName().trim().toLowerCase());
        detail.setId(id);
        return detailRepository.save(detail);
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

    @Override
    public List<Detail> findMultiple(Pageable pageable) {
        Page<Detail> foundDetails = detailRepository.findAll(pageable);
        if(!foundDetails.isEmpty()) {
            return foundDetails.getContent();
        }
        else throw new EntityNotFoundException(ENTITIES_NOT_FOUND.getMessage());
    }
}
