package com.ecommerce.shubkart.models;

import com.ecommerce.shubkart.dtos.FakeStoreProductResDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends FakeStoreProductResDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;
}
