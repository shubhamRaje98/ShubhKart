package com.ecommerce.shubkart.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Subcategory extends BaseModel{
    private String name;
    private Long countOfProduct;
}
