package com.ecommerce.shubkart.services.filteringService;

import com.ecommerce.shubkart.models.Product;

import java.util.List;

public interface Filter {
    List<Product> apply(List<Product> products,
                        List<String> allowedValues);
}
