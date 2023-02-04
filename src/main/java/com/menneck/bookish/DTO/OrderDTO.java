package com.menneck.bookish.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderDTO {

    @NotEmpty
    private Double price;

    private List<ProductDTO> productDTOList;

}
