
package com.LoyaltyPoints.LoyaltyPoints.repository;

import com.LoyaltyPoints.LoyaltyPoints.entity.PointsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointsTransactionRepository
        extends JpaRepository<PointsTransaction, Long> {

    List<PointsTransaction> findByCustomer_IdOrderByCreatedAtDesc(Long customerId);
}