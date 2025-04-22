package by.bsu.detailstorage.dtos.roledtos;

import by.bsu.detailstorage.dtos.CreateDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCreateDto implements CreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
