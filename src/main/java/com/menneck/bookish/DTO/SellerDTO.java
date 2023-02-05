package com.menneck.bookish.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SellerDTO {

    @NotNull
    private String name;
    @NotEmpty
    private Integer phone;
    @NotEmpty
    private Double comission;

}
