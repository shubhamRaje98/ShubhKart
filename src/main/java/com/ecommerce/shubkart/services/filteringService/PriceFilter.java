package com.ecommerce.shubkart.services.filteringService;

import com.ecommerce.shubkart.models.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PriceFilter implements Filter{
    /**
     * @param products
     * @param allowedValues
     * @return filterd list of products
     */
    @Override
    public List<Product> apply(List<Product> products, List<String> allowedValues) {
        List<Product> response = new ArrayList<>();
        Set<String> allowedSet = new HashSet<>(allowedValues);

        for(Product product: products){
            if(allowedSet.contains(product.getPrice())) {
                response.add(product);
            }
        }

        return response;
    }
}
