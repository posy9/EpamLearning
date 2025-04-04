package by.bsu.detailstorage.service;

import by.bsu.detailstorage.repository.DetailRepository;
import by.bsu.detailstorage.model.Detail;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DetailService {

    DetailRepository detailRepository;
    private static final String ENTITY_NOT_FOUND_MSG = "Detail with id : %s not found";
    private static final String ENTITY_EXISTS_MSG = "Detail with name : %s already exists";

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
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MSG, id));
        }
    }


    public Detail createDetail(Detail detail) {
        detail.setName(detail.getName().trim().toLowerCase());
        if (detailRepository.findByName(detail.getName()) == null) {
            detailRepository.create(detail);
            return detail;
        }
        else {
            throw new EntityExistsException(String.format(ENTITY_EXISTS_MSG, detail.getName()));
        }
    }

    public Detail updateDetail(long id, Detail detail) {
        if (detailRepository.findById(id) != null) {
            detail.setId(id);
            return detailRepository.update(detail);
        }
        else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MSG, id));
        }

    }

    public void deleteDetail(long id) {
        Detail detailForDelete = detailRepository.findById(id);
        detailRepository.delete(detailForDelete);
    }

    public List<Detail> findMultipleDetails(Pageable pageable) {
       return detailRepository.readMultiple(pageable);
    }
}
