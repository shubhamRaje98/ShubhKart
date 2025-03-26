package com.ecommerce.shubkart.dtos;

import com.ecommerce.shubkart.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;

    public static GetProductDto from(Product product){
        GetProductDto getProductResDto = new GetProductDto();
        getProductResDto.setTitle(product.getTitle());
        getProductResDto.setDescription(product.getDescription());
        getProductResDto.setPrice(product.getPrice());
        getProductResDto.setImageUrl(product.getImageUrl());
        getProductResDto.setCategoryName(product.getCategoryName());

        return getProductResDto;
    }
}
