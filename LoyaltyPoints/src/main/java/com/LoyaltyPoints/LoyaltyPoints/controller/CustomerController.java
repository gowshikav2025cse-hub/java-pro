
package com.LoyaltyPoints.LoyaltyPoints.controller;

import com.LoyaltyPoints.LoyaltyPoints.entity.Customer;
import com.LoyaltyPoints.LoyaltyPoints.entity.PointsTransaction;
import com.LoyaltyPoints.LoyaltyPoints.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping("/{id}/earn-points")
    public Customer earnPoints(
            @PathVariable Long id,
            @RequestParam double purchaseAmount) {
        return customerService.earnPoints(id, purchaseAmount);
    }

    @PostMapping("/{id}/redeem-points")
    public Customer redeemPoints(
            @PathVariable Long id,
            @RequestParam int points) {
        return customerService.redeemPoints(id, points);
    }

    @GetMapping("/{id}/transactions")
    public List<PointsTransaction> getTransactionHistory(
            @PathVariable Long id) {
        return customerService.getTransactionHistory(id);
    }
}