package com.ecommerce.shubkart.controllers;

import com.ecommerce.shubkart.dtos.payment.ProductRequest;
import com.ecommerce.shubkart.dtos.payment.StripeResponse;
import com.ecommerce.shubkart.services.paymentService.StripeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-gateway")
public class PaymentsGatewayController {
    private StripeService stripeService;

    public PaymentsGatewayController(StripeService stripeService){
         this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest){
        StripeResponse stripeResponse = stripeService.checkoutProduct(productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

    @GetMapping("/success")
    public String successfulPayment(){
        return "Payment successfull";
    }

    @GetMapping("/error")
    public String errorPayment(){
        return "Payment error";
    }
    @GetMapping("/cancel")
    public String cancledPayment(){
        return "Payment canceled";
    }
}
