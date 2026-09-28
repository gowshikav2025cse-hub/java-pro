
package com.LoyaltyPoints.LoyaltyPoints.service;

import com.LoyaltyPoints.LoyaltyPoints.entity.Customer;
import com.LoyaltyPoints.LoyaltyPoints.entity.PointsTransaction;
import com.LoyaltyPoints.LoyaltyPoints.repository.CustomerRepository;
import com.LoyaltyPoints.LoyaltyPoints.repository.PointsTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PointsTransactionRepository transactionRepository;

    public CustomerService(
            CustomerRepository customerRepository,
            PointsTransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Transactional
    public Customer earnPoints(Long id, double purchaseAmount) {
        if (purchaseAmount <= 0) {
            throw new RuntimeException(
                    "Purchase amount must be greater than zero");
        }

        Customer customer = getCustomerById(id);

        int points = (int) (purchaseAmount / 100);

        if (points <= 0) {
            throw new RuntimeException(
                    "Purchase amount must be at least 100");
        }

        int balance = customer.getPointsBalance() + points;
        customer.setPointsBalance(balance);

        if (balance >= 3000) {
            customer.setTier("PLATINUM");
        } else if (balance >= 1500) {
            customer.setTier("GOLD");
        } else if (balance >= 500) {
            customer.setTier("SILVER");
        } else {
            customer.setTier("BRONZE");
        }

        customer = customerRepository.save(customer);

        PointsTransaction transaction = new PointsTransaction();
        transaction.setCustomer(customer);
        transaction.setTransactionType("EARN");
        transaction.setPoints(points);
        transaction.setPurchaseAmount(purchaseAmount);
        transactionRepository.save(transaction);

        return customer;
    }

    @Transactional
    public Customer redeemPoints(Long id, int points) {
        Customer customer = getCustomerById(id);

        if (points <= 0) {
            throw new RuntimeException("Points must be greater than zero");
        }

        if (customer.getPointsBalance() < points) {
            throw new RuntimeException("Insufficient points");
        }

        customer.setPointsBalance(
                customer.getPointsBalance() - points);

        customer = customerRepository.save(customer);

        PointsTransaction transaction = new PointsTransaction();
        transaction.setCustomer(customer);
        transaction.setTransactionType("REDEEM");
        transaction.setPoints(points);
        transaction.setPurchaseAmount(0);
        transactionRepository.save(transaction);

        return customer;
    }

    public List<PointsTransaction> getTransactionHistory(Long customerId) {
        getCustomerById(customerId);

        return transactionRepository
                .findByCustomer_IdOrderByCreatedAtDesc(customerId);
    }
}