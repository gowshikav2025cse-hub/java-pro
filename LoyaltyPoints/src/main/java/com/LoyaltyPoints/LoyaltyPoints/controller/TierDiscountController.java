
package com.LoyaltyPoints.LoyaltyPoints.controller;

import com.LoyaltyPoints.LoyaltyPoints.entity.Customer;
import com.LoyaltyPoints.LoyaltyPoints.repository.CustomerRepository;
import com.LoyaltyPoints.LoyaltyPoints.service.TierDiscountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class TierDiscountController {

    private final CustomerRepository customerRepository;
    private final TierDiscountService tierDiscountService;

    public TierDiscountController(
            CustomerRepository customerRepository,
            TierDiscountService tierDiscountService) {
        this.customerRepository = customerRepository;
        this.tierDiscountService = tierDiscountService;
    }

    @GetMapping("/{id}/discount")
    public ResponseEntity<?> getDiscount(
            @PathVariable Long id,
            @RequestParam double amount) {

        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        String tier = customer.getTier();

        int discountPercentage =
                tierDiscountService.getDiscountPercentage(tier);

        double discount =
                tierDiscountService.calculateDiscount(amount, tier);

        double finalAmount =
                tierDiscountService.calculateFinalAmount(amount, tier);

        return ResponseEntity.ok(Map.of(
                "customerId", id,
                "tier", tier,
                "discountPercentage", discountPercentage,
                "originalAmount", amount,
                "discountAmount", discount,
                "finalAmount", finalAmount
        ));
    }
}