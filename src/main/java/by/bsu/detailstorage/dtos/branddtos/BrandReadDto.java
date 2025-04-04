package by.bsu.detailstorage.dtos.branddtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class BrandReadDto {

    @NotNull(message = "should not be empty")
    private Long id;

    private String name;

}
