package com.fraud.analyzer.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fraud.analyzer.model.Transaction;

public class RuleEngine {
    // Mapping accountID with historical data
    private final Map<String, AccountMetaData> history = new HashMap<>();

    // Thresholds (limits)
    private static final double SPIKE_FACTOR = 5.0; // 5x then average is suspicious
    private static final long SPEED_THRESHOLD_MS = 1000 * 60 * 60; // 1 hour
    private static final double MAX_AMOUNT_LIMIT = 50000;

    public List<String> calculateRisk(Transaction tx){
        List<String> reasons = new ArrayList<>();
        
        // Get user history
        AccountMetaData stats = history.computeIfAbsent(tx.sourceAccountId, k -> new AccountMetaData());

        // RULE 1: LARGE AMOUNT SPIKE (Last K Transactions)
        if(stats.getRecentCount() >= 2){
            double avg = stats.getRecentAverage();
            if(tx.amount > (avg * SPIKE_FACTOR) && avg > 0){
                reasons.add("AMOUNT_SPIKE");
            }
        }

        // RULE 2: IMPOSSIBLE TRAVEL
        if(stats.hasLocation()){
            long timeDiff = tx.timestamp - stats.getLastTimestamp();
            double distance = getDistance(stats.getLastLat(), stats.getLastLon(), tx.latitude, tx.longitude);

            if(distance > 500 && timeDiff < SPEED_THRESHOLD_MS){
                reasons.add("IMPOSSIBLE_TRAVEL");
            }
        }

        // RULE 3: ABSOLUTE HIGH LIMIT
        if(tx.amount > MAX_AMOUNT_LIMIT){
            reasons.add("HIGH_AMOUNT");
        }

        // Update History
        stats.update(tx.amount, tx.latitude, tx.longitude, tx.timestamp);
        
        return reasons;
    }
    
    private double getDistance(double lat1, double lon1, double lat2, double lon2){
        double R = 6371;

        double dlat = Math.toRadians(lat2 - lat1);
        double dlon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dlon / 2) * Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

}