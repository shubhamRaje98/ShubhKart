package com.ecommerce.shubkart.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product> featuredProducts;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Subcategory> subcategory;

    @Column(columnDefinition = "BIGINT DEFAULT 0")
    private Long countOfProducts;

    @PrePersist
    public void setDefaultCountOfProducts() {
        if (this.countOfProducts == null) { // Only set default if it's not already initialized
            this.countOfProducts = 0L;// Set the default value to 0
        }
    }
}
