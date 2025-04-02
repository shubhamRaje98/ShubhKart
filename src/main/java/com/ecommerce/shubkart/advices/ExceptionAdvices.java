package com.ecommerce.shubkart.advices;

import com.ecommerce.shubkart.dtos.ErrorResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRuntimeException(RuntimeException e){
        ErrorResponseDto dto = new ErrorResponseDto();
        dto.setStatus("runtime error");
        dto.setMessage(e.getMessage());
        return dto;
    }
    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "something went wrong";
    }
}
