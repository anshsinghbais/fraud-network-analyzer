package com.fraud.analyzer.model;

public class Transaction {

    public String transactionId;
    public String sourceId;
    public String destId;
    public double amount;
    public long timestamp;

    // 🌍 Coordinates
    public double latitude;
    public double longitude;

    public Transaction(String transactionId,
                       String sourceId,
                       String destId,
                       double amount,
                       long timestamp,
                       double latitude,
                       double longitude) {

        this.transactionId = transactionId;
        this.sourceId = sourceId;
        this.destId = destId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "TX[" +
                "ID=" + transactionId +
                ", " + sourceId + " -> " + destId +
                ", ₹" + amount +
                ", lat=" + latitude +
                ", lon=" + longitude +
                "]";
    }
}