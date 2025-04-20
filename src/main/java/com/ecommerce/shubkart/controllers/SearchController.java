package com.ecommerce.shubkart.controllers;

import com.ecommerce.shubkart.dtos.GetAllProductsResDto;
import com.ecommerce.shubkart.dtos.GetProductDto;
import com.ecommerce.shubkart.dtos.search.FilterDto;
import com.ecommerce.shubkart.dtos.search.SearchResponseDto;
import com.ecommerce.shubkart.dtos.search.SortingCriteria;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.services.searchService.SearchService;

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

    @GetMapping("/")
    public SearchResponseDto search(@RequestParam("query") String query,
                                    @RequestParam("filters")List<FilterDto> filters,
                                    @RequestParam("sortBy")SortingCriteria sortingCriteria,
                                    @RequestParam("pageNumber") int pageNumber,
                                    @RequestParam("pageSize") int pageSize){
        SearchResponseDto searchResponseDto = new SearchResponseDto();
        return searchResponseDto;
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
