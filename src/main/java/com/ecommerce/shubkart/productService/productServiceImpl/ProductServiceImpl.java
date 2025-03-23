package com.ecommerce.shubkart.productService.productServiceImpl;

import com.ecommerce.shubkart.dtos.FakeStoreProductReqDto;
import com.ecommerce.shubkart.dtos.FakeStoreProductResDto;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.productService.ProductService;
import org.springframework.boot.autoconfigure.http.client.HttpClientProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    private final RestTemplate restTemplate;
    public ProductServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product createProduct(Product product) {
        FakeStoreProductReqDto req = new FakeStoreProductReqDto();
        req.setTitle(product.getTitle());
        req.setDescription(product.getDescription());
        req.setPrice(product.getPrice());
        req.setImage(product.getImageUrl());
        req.setCategory(product.getCategoryName());

        FakeStoreProductResDto response =  restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                req,
                FakeStoreProductResDto.class
        );

        return FakeStoreProductResDto.getProduct(response);
    }

}
