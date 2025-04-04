package by.bsu.detailstorage.dtos.userdtos;

import by.bsu.detailstorage.dtos.roledtos.RoleUpdateDto;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String login;

    private String password;

    private RoleUpdateDto role;
}
