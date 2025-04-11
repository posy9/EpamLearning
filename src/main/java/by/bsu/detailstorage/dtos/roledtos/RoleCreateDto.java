package by.bsu.detailstorage.dtos.roledtos;

import by.bsu.detailstorage.dtos.CreateDto;
import lombok.Data;

@Data
public class RoleCreateDto implements CreateDto {

    private String name;
}
