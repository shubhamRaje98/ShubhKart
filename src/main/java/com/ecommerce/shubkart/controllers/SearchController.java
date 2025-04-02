package com.ecommerce.shubkart.controllers;

import com.ecommerce.shubkart.dtos.GetAllProductsResDto;
import com.ecommerce.shubkart.dtos.GetProductDto;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.productService.searchService.SearchService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/product/subcategory")
    public GetAllProductsResDto searchBySubcategoryName(@RequestParam("subcategory") String subCategory){
        List<Product> products = searchService.searchBySubCategoryName(subCategory);
        List<GetProductDto> productResList = new ArrayList<>();

        for(Product product: products){
            productResList.add(GetProductDto.from(product));
        }
        GetAllProductsResDto allProducts = new GetAllProductsResDto();
        allProducts.setProducts(productResList);

        return allProducts;
    }
}
