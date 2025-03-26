package com.ecommerce.shubkart.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllProductsResDto {
    private List<GetProductDto> products;
}