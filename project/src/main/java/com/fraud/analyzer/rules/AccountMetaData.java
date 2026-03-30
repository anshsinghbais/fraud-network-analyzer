package com.fraud.analyzer.rules;

import java.util.LinkedList;
import java.util.Queue;

public class AccountMetaData {
    private Queue<Double> lastKTransaction = new LinkedList<>();
    private double recentSum = 0;
    private static final int K = 6;


    private double lastLat = 0;
    private double lastLon = 0;
    private long lastTimestamp = 0;
    private boolean hasLocation = false;

    public void update(double amount, double lat, double lon, long timestamp){
        
        // Maintain last K transactions
        lastKTransaction.add(amount);
        recentSum += amount;
        if(lastKTransaction.size() > K){
            recentSum -= lastKTransaction.poll();
        }
        this.lastLat = lat;
        this.lastLon = lon;
        this.lastTimestamp = timestamp;
        this.hasLocation = true;
    }
    
    public double getRecentAverage(){
        if(lastKTransaction.isEmpty()) return 0;
        return recentSum / lastKTransaction.size();
    }
    
    public double getLastLat() {
        return lastLat;
    }

     public double getLastLon() {
        return lastLon;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public boolean hasLocation() {
        return hasLocation;
    }
    public int getRecentCount() { 
        return lastKTransaction.size(); 
    }
}
