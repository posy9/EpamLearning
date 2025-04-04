package by.bsu.detailstorage.dtos.userdtos;

import by.bsu.detailstorage.dtos.roledtos.RoleCreateDto;
import lombok.Data;

@Data
public class UserCreateDto {

    private String login;

    private String password;

    private RoleCreateDto role;
}
