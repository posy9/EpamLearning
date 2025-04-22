package by.bsu.detailstorage.dtos.userdtos;

import by.bsu.detailstorage.dtos.CreateDto;
import by.bsu.detailstorage.dtos.roledtos.RoleReadDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateDto implements CreateDto {

    @NotBlank(message = "should not be empty")
    private String login;

    @NotBlank(message = "should not be empty")
    private String password;


    private RoleReadDto role;
}
