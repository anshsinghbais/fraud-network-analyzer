package com.fraud.analyzer.generator;

import java.io.FileWriter;
import java.io.IOException;

import com.fraud.analyzer.model.Transaction;

public class Logger {

    private static final String FILE_NAME = "transactions.log";

    public static void logTransaction(Transaction tx) {
        String log = "[TRANSACTION] " + tx;
        System.out.println(log);
        writeToFile(log);
    }

    public static void logFraud(Transaction tx) {
        String log = "[FRAUD]  " + tx;
        System.out.println(log);
        writeToFile(log);
    }

    private static void writeToFile(String message) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(message + "\n");
        } catch (IOException e) {
            System.out.println("[ERROR] Logging failed");
        }
    }
    public static void logError(String message) {
    System.out.println("[ERROR] " + message);
}
}