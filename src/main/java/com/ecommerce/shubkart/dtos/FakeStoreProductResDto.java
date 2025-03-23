package com.ecommerce.shubkart.dtos;

import com.ecommerce.shubkart.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductResDto {
    private long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public static Product getProduct(FakeStoreProductResDto response){
        Product product = new Product();
        product.setTitle(response.getTitle());
        product.setPrice(response.getPrice());
        product.setId(response.getId());
        product.setImageUrl(response.getImage());
        product.setDescription(response.getDescription());
        product.setCategoryName(response.getCategory());
        return product;
    }
}
