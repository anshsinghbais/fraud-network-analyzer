package com.fraud.analyzer.generator;

import java.util.Random;
import java.util.UUID;

import com.fraud.analyzer.model.Transaction;

public class TransactionStream{
    private static final String[] ACCOUNTS = {"ACC1", "ACC2", "ACC3", "ACC4", "ACC5"};
    private static final String[] LOCATIONS = {"IN-MP", "IN-DL", "US-NY", "UK-LON"};

    private final Random random = new Random();

    public Transaction generateTransaction(){
        String source = ACCOUNTS[random.nextInt(ACCOUNTS.length)];
        String dest = ACCOUNTS[random.nextInt(ACCOUNTS.length)];

        while(source.equals(dest)){
            dest = ACCOUNTS[random.nextInt(ACCOUNTS.length)];
        }
        boolean isFraud = random.nextInt(10) < 2;

        double amount;
        String location;

        if (isFraud){
            amount = 10000 + random.nextInt(50000);
            location = LOCATIONS[random.nextInt(LOCATIONS.length)];
        } else {
            amount = 100 + random.nextInt(5000);
            location = "IN-MP";
        }

        return new Transaction(UUID.randomUUID().toString(), source, dest,
                               amount, System.currentTimeMillis(), location);
    }
    public void startStream(){

        while (true) { 
            Transaction tx = generateTransaction();
            System.err.println(tx);

            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }}
    }