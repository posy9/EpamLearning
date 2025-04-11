package by.bsu.detailstorage.dtos.userdtos;

import by.bsu.detailstorage.dtos.CreateDto;
import by.bsu.detailstorage.dtos.roledtos.RoleReadDto;
import lombok.Data;

@Data
public class UserCreateDto implements CreateDto {

    private String login;

    private String password;

    private RoleReadDto role;
}
