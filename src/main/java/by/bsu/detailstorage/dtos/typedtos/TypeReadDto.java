package by.bsu.detailstorage.dtos.typedtos;

import by.bsu.detailstorage.dtos.ReadDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TypeReadDto implements ReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    private String name;
}
