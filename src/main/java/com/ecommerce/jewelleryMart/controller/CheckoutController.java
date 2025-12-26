package com.ecommerce.jewelleryMart.controller;

import com.ecommerce.jewelleryMart.dto.PaymentRequest;
import com.ecommerce.jewelleryMart.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/checkout")
@CrossOrigin(origins = "*")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCartSummary(@PathVariable String userId) {
        try {
            Map<String, Object> summary = checkoutService.getCartSummary(userId);
            return new ResponseEntity<>(summary, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred processing the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<?> confirmPayment(@RequestBody PaymentRequest request) {
        try {
            Map<String, Object> invoice = checkoutService.processPayment(request);
            return ResponseEntity.ok(invoice);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Payment processing failed."));
        }
    }
}