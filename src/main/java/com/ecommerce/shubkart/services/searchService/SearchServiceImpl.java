package com.ecommerce.shubkart.services.searchService;

import com.ecommerce.shubkart.dtos.search.FilterDto;
import com.ecommerce.shubkart.dtos.search.SortingCriteria;
import com.ecommerce.shubkart.models.Product;
import com.ecommerce.shubkart.repositories.ProductRepository;
import com.ecommerce.shubkart.services.RedisService.RedisService;
import com.ecommerce.shubkart.services.filteringService.FilterFactory;
import com.ecommerce.shubkart.services.sortingService.SorterFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    ProductRepository productRepository;

    private RedisService redisService;

    public SearchServiceImpl(ProductRepository productRepository, RedisService redisService){
        this.productRepository = productRepository;
        this.redisService = redisService;
    }
    @Override
    public List<Product> searchBySubCategoryName(String name) {
        return productRepository.findAllByCategory_Subcategory_nameEquals(name);
    }

    @Override
    public Page<Product> search(String query, List<FilterDto> filters, SortingCriteria sortingCriteria, int pageNumber, int pageSize) {
        List<Product> products = new ArrayList<>();
        List<Product> productCache = redisService.get(query, List.class);

        if(productCache!=null){
            products = productCache;
        }else {
            products = productRepository.findByTitleContaining(query);
            redisService.set("product_query_"+query, products, 60l);
        }

        for(FilterDto filterDto: filters){
            products = FilterFactory.getFilterFromKey(
                    filterDto.getKey()
            ).apply(products, filterDto.getValues());
        }

        products = SorterFactory.getSorterByCriteria(sortingCriteria).sort(products);

        List<Product> productsOnPage = new ArrayList<>();

        for (int i = pageSize * (pageNumber - 1); i <= (pageSize * pageNumber) - 1; ++i) {
            productsOnPage.add(products.get(i));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize); // Adjust page number and size as needed
        return new PageImpl<>(productsOnPage, pageable, products.size());
    }
}
