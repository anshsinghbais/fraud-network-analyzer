package com.fraud.analyzer.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphManager {

    // Adjacency list: source → list of destinations
    private Map<String, List<String>> graph = new HashMap<>();

    // Track incoming transactions for each account
    private Map<String, Integer> incomingCount = new HashMap<>();

    // Add transaction to graph
    public void addTransaction(String source, String dest) {

        // Add edge: source → dest
        graph.putIfAbsent(source, new ArrayList<>());
        graph.get(source).add(dest);

        // Track incoming edges
        incomingCount.put(dest, incomingCount.getOrDefault(dest, 0) + 1);

        // Debug print
        System.out.println("[GRAPH] " + source + " -> " + dest);

        // Detect fraud patterns
        detectFraud(dest);
    }

    // Detect simple graph-based fraud
    private void detectFraud(String account) {

        // Fraud Hub: many incoming transactions
        if (incomingCount.get(account) >= 3) {
            System.out.println("[GRAPH ALERT]  " + account + 
                " is receiving transactions from multiple accounts!");
        }
    }
}