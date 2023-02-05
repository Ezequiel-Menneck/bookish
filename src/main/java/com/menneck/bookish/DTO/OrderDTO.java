package com.menneck.bookish.DTO;

import com.menneck.bookish.Model.Product;
import com.menneck.bookish.Model.Seller;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderDTO {

    @NotEmpty
    private Double price;

    private List<Product> productList;

    private Seller seller;

}
