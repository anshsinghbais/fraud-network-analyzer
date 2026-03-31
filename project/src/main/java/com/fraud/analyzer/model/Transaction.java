package com.fraud.analyzer.model;

public class Transaction {

    public String transactionId;
    public String sourceAccountId;
    public String destAccountId;
    public double amount;
    public long timestamp;

    // 🌍 Coordinates
    public double latitude;
    public double longitude;

    public Transaction(String transactionId,
                       String sourceAccountId,
                       String destAccountId,
                       double amount,
                       long timestamp,
                       double latitude,
                       double longitude) {

        this.transactionId = transactionId;
        this.sourceAccountId = sourceAccountId;
        this.destAccountId = destAccountId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "TX[" +
                "ID=" + transactionId +
                ", " + sourceAccountId + " -> " + destAccountId +
                ", ₹" + amount +
                ", lat=" + latitude +
                ", lon=" + longitude +
                "]";
    }
        public String getSourceAccountId() {
            return sourceAccountId;
    }

        public String getDestAccountId() {
            return destAccountId;
        }
        public long getTimestamp() {
            return timestamp;
        }
}
