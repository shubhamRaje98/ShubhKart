package com.ecommerce.shubkart.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductReqDto {
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;
}
