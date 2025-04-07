package by.bsu.detailstorage.dtos.countrydtos;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class CountryCreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
