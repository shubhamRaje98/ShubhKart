package com.ecommerce.shubkart.services.productService.searchService;

import com.ecommerce.shubkart.models.Product;

import java.util.List;

public interface SearchService {

    List<Product> searchBySubCategoryName(String name);
}
