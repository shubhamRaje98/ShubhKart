package com.ecommerce.shubkart.controllers;

import com.ecommerce.shubkart.dtos.CreateProductReqDto;
import com.ecommerce.shubkart.dtos.CreateProductResDto;
import com.ecommerce.shubkart.dtos.GetAllProductsResDto;
import com.ecommerce.shubkart.dtos.GetProductDto;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.productService.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    /*
    Constructor injection is recommended practice over Autowired(field injection)
    cause of immutability(cause dependencies are set final in constructor) and
    easy unit testing(cause field level injection hides dependencies while testing) better code readability
    -----Service Design
    Service method shouldn't take a DTO, service should only take exact attributes that is needed or can pass model obj
        - Cause the same service method might be called by any controller method
    ----DTO Design
    For every req we should use req DTO and res DTO instead of returning model obj
        - Your req DTO should have a method to convert req to corresponding model obj
        - your res DTO should have a method to convert model obj to corresponding res obj
    ----Configured beans
    If objects of some classes not in spring IOC by default e.g RestTemplate
        - make method return it in configuration package (using @Bean)
    */
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/check")
    public String check(){
        return "Its up";
    }

    @PostMapping("/product")
    public CreateProductReqDto createProduct(@RequestBody CreateProductReqDto createProductReqDto){
        Product product = productService.createProduct(
            createProductReqDto.toProduct()
        );
        return CreateProductResDto.fromProduct(product);
    }
    @PutMapping("/product/{id}")
    public boolean updateProduct(@RequestBody Product product, @PathVariable long id){
        return productService.updateProduct(id, product);

    }

    @DeleteMapping("/product/{id}")
    public boolean deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }

    @GetMapping("/products")
    public GetAllProductsResDto getAllProducts(){
        List<Product> products = productService.getAllProducts();
        GetAllProductsResDto response = new GetAllProductsResDto();

        List<GetProductDto> getProductResDto = new ArrayList<>();

        for(Product product: products){
            getProductResDto.add(GetProductDto.from(product));
        }

        response.setProducts(getProductResDto);

        return response;
   }


}
