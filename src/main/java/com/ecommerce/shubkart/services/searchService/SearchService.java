package com.ecommerce.shubkart.services.searchService;

import com.ecommerce.shubkart.dtos.search.FilterDto;
import com.ecommerce.shubkart.dtos.search.SortingCriteria;
import com.ecommerce.shubkart.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SearchService {

    List<Product> searchBySubCategoryName(String name);
    public Page<Product> search(String query, List<FilterDto> filters, SortingCriteria sortingCriteria, int pageNumber, int pageSize);
}
