
package com.LoyaltyPoints.LoyaltyPoints.repository;

import com.LoyaltyPoints.LoyaltyPoints.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Long> {
}