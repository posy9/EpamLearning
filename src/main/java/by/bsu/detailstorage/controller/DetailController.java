package by.bsu.detailstorage.controller;

import by.bsu.detailstorage.dtos.detaildtos.DetailCreateDto;
import by.bsu.detailstorage.dtos.detaildtos.DetailForListDto;
import by.bsu.detailstorage.dtos.detaildtos.DetailReadDto;
import by.bsu.detailstorage.model.Detail;
import by.bsu.detailstorage.service.DetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/details",consumes = {"application/json"}, produces = {"application/json"})
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;
    private final ModelMapper modelMapper;

    @GetMapping
    List<DetailForListDto> getAllDetails(Pageable pageable) {
       List<Detail> details = detailService.findMultiple(pageable);
        return details.stream()
                .map(detail -> modelMapper.map(detail, DetailForListDto.class))
                .toList();
    }

    @GetMapping(value = "/{id}")
    DetailReadDto getDetailById(@PathVariable long id) {
        Detail detail= detailService.findById(id);
        return modelMapper.map(detail, DetailReadDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DetailReadDto createDetail(@Valid @RequestBody DetailCreateDto detailCreateDto) {
        Detail detail = modelMapper.map(detailCreateDto, Detail.class);
        detailService.createEntity(detail);
        Detail createdDetail = detailService.findById(detail.getId());
        return modelMapper.map(createdDetail, DetailReadDto.class);
    }

    @PutMapping(value = "/{id}")
    DetailReadDto updateDetail(@PathVariable long id, @RequestBody DetailCreateDto detailCreateDto) {
        detailService.updateEntity(id, modelMapper.map(detailCreateDto, Detail.class));
        Detail updatedDetail = detailService.findById(id);
        return modelMapper.map(updatedDetail, DetailReadDto.class);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteDetail(@PathVariable long id) {
        detailService.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }
}
