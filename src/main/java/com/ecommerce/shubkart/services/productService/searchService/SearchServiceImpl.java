package com.ecommerce.shubkart.services.productService.searchService;

import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    ProductRepository productRepository;

    public SearchServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<Product> searchBySubCategoryName(String name) {
        return productRepository.findAllByCategory_Subcategory_nameEquals(name);
    }
}
