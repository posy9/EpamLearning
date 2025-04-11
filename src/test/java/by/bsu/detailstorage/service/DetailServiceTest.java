package by.bsu.detailstorage.service;


import by.bsu.detailstorage.model.Detail;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DetailServiceTest {

    @Mock
    private DetailRepository detailRepository;

    @InjectMocks
    private DetailService detailService;

    @Test
    public void createEntity_shouldThrowEntityExistsException_whenEntityAlreadyExists(){
        Detail inputDetail = new Detail();
        inputDetail.setName("name");
        when(detailRepository.create(inputDetail)).thenThrow(ConstraintViolationException.class);
        assertThrows(EntityExistsException.class, () -> detailService.createEntity(inputDetail));
    }

    @Test
    public void updateEntity_shouldThrowEntityNotFoundException_whenIdDoesNotExists(){
        Detail inputDetail = new Detail();
        inputDetail.setName("name");
        when(detailRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> detailService.updateEntity(1L, inputDetail));
    }

    @Test
    public void updateEntity_shouldThrowEntityExistsException_whenEntityAlreadyExists(){
        Detail inputDetail = new Detail();
        inputDetail.setName("name");
        when(detailRepository.findById(1L)).thenReturn(Optional.of(inputDetail));
        when(detailRepository.findByName("name")).thenReturn(Optional.of(inputDetail));
        assertThrows(EntityExistsException.class, () -> detailService.updateEntity(1L, inputDetail));
    }

    @Test
    public void deleteEntity_shouldThrowEntityNotFoundException_whenIdDoesNotExists(){
        when(detailRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> detailService.deleteEntity(1L));
    }

    @Test
    public void findById_shouldThrowEntityNotFoundException_whenIdDoesNotExists(){
        when(detailRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> detailService.findById(1L));
    }

    @Test
    public void findByName_shouldThrowEntityNotFoundException_whenEntitiesDoesNotExist(){
        when(detailRepository.readMultiple(any(Pageable.class))).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class, () -> detailService.findMultiple(Pageable.unpaged()));
    }
}
