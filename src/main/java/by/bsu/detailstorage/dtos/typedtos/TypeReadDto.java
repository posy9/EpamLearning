package by.bsu.detailstorage.dtos.typedtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TypeReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    private String name;

}
