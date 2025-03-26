package com.ecommerce.shubkart.productService;

import com.ecommerce.shubkart.models.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product);

    public boolean updateProduct(long id, Product product);
    public boolean deleteProduct(long id);

    public List<Product> getAllProducts();
}
