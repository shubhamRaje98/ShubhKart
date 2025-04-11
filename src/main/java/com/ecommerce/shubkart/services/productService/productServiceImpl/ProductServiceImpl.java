package com.ecommerce.shubkart.services.productService.productServiceImpl;

import com.ecommerce.shubkart.dtos.FakeStoreProductReqDto;
import com.ecommerce.shubkart.dtos.FakeStoreProductResDto;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.services.productService.ProductService;
//import org.springframework.boot.autoconfigure.http.client.HttpClientProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        //req.setCategory(product.getCategoryName());

        FakeStoreProductResDto response =  restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                req,
                FakeStoreProductResDto.class
        );

        return FakeStoreProductResDto.getProduct(response);
    }

    @Override
    public boolean updateProduct(long id, Product product){
        try{
            restTemplate.put(
                    "https://fakestoreapi.com/products/"+id,
                    product
            );
        }catch (Exception e){
            return false;
        }

        return true;
    }

    @Override
    public boolean deleteProduct(long id){
        try{
            System.out.println("deleting the product...");
            restTemplate.delete("https://fakestoreapi.com/products/"+id);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getAllProducts(){
//        FakeStoreProductResDto[] response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                FakeStoreProductResDto[].class
//        );
//
//        List<FakeStoreProductResDto> responseDtoList =
//                Stream.of(response).toList();
//
//        List<Product> products = new ArrayList<>();
//
//        for(FakeStoreProductResDto fakeStoreProductResDto : responseDtoList){
//            products.add(FakeStoreProductResDto.getProduct(fakeStoreProductResDto));
//        }
//
//        return products;
        throw new RuntimeException();
    }

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }


}
