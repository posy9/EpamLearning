package by.bsu.detailstorage.dtos.roledtos;

import by.bsu.detailstorage.dtos.ReadDto;
import lombok.Data;

@Data
public class RoleReadDto implements ReadDto {

    private Long id;

    private String name;
}
