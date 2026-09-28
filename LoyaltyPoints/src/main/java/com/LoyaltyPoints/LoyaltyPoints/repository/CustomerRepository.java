
package com.LoyaltyPoints.LoyaltyPoints.repository;

import com.LoyaltyPoints.LoyaltyPoints.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}