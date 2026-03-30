package com.fraud.analyzer;

import com.fraud.analyzer.generator.TransactionStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Fraud Analyzer...");

        TransactionStream stream = new TransactionStream();
        stream.startStream();
    }
}
