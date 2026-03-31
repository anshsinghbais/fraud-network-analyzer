package com.fraud.analyzer.graph;

import java.util.LinkedList;
import java.util.Queue;

public class TransactionSlidingWindow {

    private Queue<Long> timestamps = new LinkedList<>();
    private long windowSizeMillis;

    public TransactionSlidingWindow(long windowSizeMillis) {
        this.windowSizeMillis = windowSizeMillis;
    }

    public void addTransaction(long timestamp) {
        timestamps.add(timestamp);
        cleanOld(timestamp);
    }

    private void cleanOld(long currentTime) {
        while (!timestamps.isEmpty() && currentTime - timestamps.peek() > windowSizeMillis) {
            timestamps.poll();
        }
    }

    public int getTransactionCount() {
        return timestamps.size();
    }

    public boolean isBurstDetected(int threshold) {
        return timestamps.size() >= threshold;
    }
}