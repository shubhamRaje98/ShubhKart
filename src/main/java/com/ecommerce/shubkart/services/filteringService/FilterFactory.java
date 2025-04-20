package com.ecommerce.shubkart.services.filteringService;

public class FilterFactory {

    public static Filter getFilterFromKey(String key) {
        return switch (key) {
            case "price" -> new PriceFilter();
            case "brand" -> new BrandFilter();
            case null, default -> null;
        };
    }
}
