package com.ecommerce.shubkart.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ErrorResponseDto {
    private String status;
    private String message;
}
