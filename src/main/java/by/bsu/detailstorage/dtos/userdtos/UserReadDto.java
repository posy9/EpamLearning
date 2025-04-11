package by.bsu.detailstorage.dtos.userdtos;

import by.bsu.detailstorage.dtos.ReadDto;
import by.bsu.detailstorage.dtos.roledtos.RoleReadDto;
import lombok.Data;

@Data
public class UserReadDto implements ReadDto {

    private Long id;

    private String login;

    private RoleReadDto role;
}
