package com.menneck.bookish.DTO;

import com.menneck.bookish.Model.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class ProductDTO {

    @NotBlank
    private String name;

    @NotEmpty
    private Double price;

    @NotNull
    private Category categories;

}
