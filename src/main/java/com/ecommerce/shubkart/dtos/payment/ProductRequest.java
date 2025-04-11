package com.ecommerce.shubkart.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private double price;
    private double quantity;
    private String name;
    private String currency;
}
