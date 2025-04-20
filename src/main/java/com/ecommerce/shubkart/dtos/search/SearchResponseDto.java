package com.ecommerce.shubkart.dtos.search;

import com.ecommerce.shubkart.dtos.GetProductDto;
import lombok.Data;
import org.springframework.data.domain.Page;


@Data
public class SearchResponseDto {
    private Page<GetProductDto> products;
}
