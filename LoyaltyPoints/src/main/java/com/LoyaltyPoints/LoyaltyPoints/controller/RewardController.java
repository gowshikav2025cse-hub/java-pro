package com.LoyaltyPoints.LoyaltyPoints.controller;

import com.LoyaltyPoints.LoyaltyPoints.entity.Reward;
import com.LoyaltyPoints.LoyaltyPoints.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    @PostMapping
    public Reward addReward(@RequestBody Reward reward) {
        return rewardService.addReward(reward);
    }

    @GetMapping("/{id}")
    public Reward getRewardById(@PathVariable Long id) {
        return rewardService.getRewardById(id);
    }
}