package com.fraud.analyzer.generator;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.fraud.analyzer.model.Transaction;
import com.fraud.analyzer.rules.RuleEngine;

public class TransactionStream{
    private static final String[] ACCOUNTS = {"ACC1", "ACC2", "ACC3", "ACC4", "ACC5"};

    private final Random random = new Random();

    public Transaction generateTransaction(){
        String source = ACCOUNTS[random.nextInt(ACCOUNTS.length)];
        String dest = ACCOUNTS[random.nextInt(ACCOUNTS.length)];

        while(source.equals(dest)){
            dest = ACCOUNTS[random.nextInt(ACCOUNTS.length)];
        }
        boolean isFraud = random.nextInt(10) < 2;

        double amount;
        double latitude;
        double longitude;

        if (isFraud){
            amount = 50000 + random.nextInt(50000);
            latitude = -90 + (180 * random.nextDouble());
            longitude = -180 + (360 * random.nextDouble());
        } else {
            amount = 100 + random.nextInt(5000);
            latitude = 23.0 + random.nextDouble();
            longitude = 77.0 + random.nextDouble();
        }

        return new Transaction(UUID.randomUUID().toString(),source,dest,amount,
                                System.currentTimeMillis(),latitude,longitude);
    }
    public void startStream(){
        RuleEngine engine = new RuleEngine();
        while (true) { 
            
        Transaction tx = generateTransaction();

        List<String> flags = null;

        try {
        flags = engine.calculateRisk(tx);
        } catch (Exception e) {
        Logger.logError("Rule engine failed: " + e.getMessage());
        }

        if (flags != null && !flags.isEmpty()) {
        Logger.logFraud(tx);
        System.out.println("Reasons: " + flags);
        } else {
        Logger.logTransaction(tx);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        }}