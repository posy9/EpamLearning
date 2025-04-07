package by.bsu.detailstorage.dtos.detaildtos;

import by.bsu.detailstorage.dtos.typedtos.TypeReadDto;
import lombok.Data;

@Data
public class DetailForListDto {

    private Long id;

    private String name;

    private TypeReadDto type;

    private int amount;
}
