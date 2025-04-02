package com.ecommerce.shubkart.dtos;

import com.ecommerce.shubkart.models.Category;
import com.ecommerce.shubkart.models.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class CreateProductReqDto {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;

    public Product toProduct(){
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category category = new Category();
        category.setName(this.categoryName);
        product.setCategory(category);

        return product;
    }
    @Override
    public String toString(){
        return title+ " "+price;
    }
}
