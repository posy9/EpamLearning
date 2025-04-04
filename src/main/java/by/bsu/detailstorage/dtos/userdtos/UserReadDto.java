package by.bsu.detailstorage.dtos.userdtos;

import by.bsu.detailstorage.dtos.roledtos.RoleReadDto;
import lombok.Data;

@Data
public class UserReadDto {

    private Long id;

    private String login;

    private String password;

    private RoleReadDto role;
}
