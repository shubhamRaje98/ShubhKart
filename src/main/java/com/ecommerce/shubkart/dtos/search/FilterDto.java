package com.ecommerce.shubkart.dtos.search;

import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    private String key;
    private List<String> values;
}
