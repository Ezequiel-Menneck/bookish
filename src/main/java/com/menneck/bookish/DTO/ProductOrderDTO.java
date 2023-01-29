package com.menneck.bookish.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ProductOrderDTO {

    @NotEmpty
    private Double price;

    private List<ProductDTO> productDTOList;

}
