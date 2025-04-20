package com.ecommerce.shubkart.services.sortingService;

import com.ecommerce.shubkart.models.Product;

import java.util.List;

public interface Sorter {
    List<Product> sort(List<Product> products);
}