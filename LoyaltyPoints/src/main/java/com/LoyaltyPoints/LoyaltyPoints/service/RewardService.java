
package com.LoyaltyPoints.LoyaltyPoints.service;

import com.LoyaltyPoints.LoyaltyPoints.entity.Reward;
import com.LoyaltyPoints.LoyaltyPoints.repository.RewardRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    public Reward addReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    public Reward getRewardById(Long id) {
        return rewardRepository.findById(id).orElse(null);
    }
}