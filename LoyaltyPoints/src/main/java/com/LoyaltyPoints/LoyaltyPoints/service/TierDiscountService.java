
package com.LoyaltyPoints.LoyaltyPoints.service;

import org.springframework.stereotype.Service;

@Service
public class TierDiscountService {

    public int getDiscountPercentage(String tier) {

        if (tier == null) {
            return 0;
        }

        switch (tier.toUpperCase()) {
            case "BRONZE":
                return 5;
            case "SILVER":
                return 10;
            case "GOLD":
                return 15;
            case "PLATINUM":
                return 20;
            default:
                return 0;
        }
    }

    public double calculateDiscount(double amount, String tier) {
        int percentage = getDiscountPercentage(tier);
        return amount * percentage / 100.0;
    }

    public double calculateFinalAmount(double amount, String tier) {
        return amount - calculateDiscount(amount, tier);
    }
}