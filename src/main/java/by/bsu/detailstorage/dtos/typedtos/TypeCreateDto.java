package by.bsu.detailstorage.dtos.typedtos;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
public class TypeCreateDto {

    @NotBlank(message = "should not be empty")
    private String name;
}
