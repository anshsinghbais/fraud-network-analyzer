package com.fraud.analyzer.graph;

import com.fraud.analyzer.model.Transaction;

public class TransactionProcessor {

    private GraphManager graph;
    private TransactionSlidingWindow window;

    public TransactionProcessor() {
        this.graph = new GraphManager();
        this.window = new TransactionSlidingWindow(4500);
    }

    public void process(Transaction tx) {

        // Add to graph
        graph.addTransaction(
                tx.getSourceAccountId(),
                tx.getDestAccountId()
        );

        window.addTransaction(tx.getTimestamp());

        // Detect burst fraud
        if (window.isBurstDetected(5)) {
            System.out.println("[GRAPH ALERT] Burst transaction activity detected!");
        }
    }
}