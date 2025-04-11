package com.ecommerce.shubkart.dtos;

import com.ecommerce.shubkart.models.Category;
import com.ecommerce.shubkart.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductResDto {
    private long id;
    private String title;
    private String description;
    private String price;
    private String imageUrl;
    private String currency;
    public static CreateProductReqDto fromProduct(Product product){
        CreateProductReqDto responseDto = new CreateProductReqDto();
        responseDto.setTitle(product.getTitle());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCurrency(product.getCurrency());
        responseDto.setCategoryName(product.getCategory().getName());


        return responseDto;
    }
}
