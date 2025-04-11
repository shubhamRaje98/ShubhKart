package com.ecommerce.shubkart.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String currency;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}