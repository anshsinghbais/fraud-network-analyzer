package com.fraud.analyzer.model;

public class Transaction {

    public String txId;
    public String sourceId;
    public String targetId;
    public double amount;
    public long timestamp;
    public String location;
 
    
    public Transaction(String txId, String sourceId, String targetId, double amount, long timestamp, String location)
    {
        this.txId = txId;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.amount = amount;
        this.timestamp = System.currentTimeMillis();
        this.location = location;
    }
}