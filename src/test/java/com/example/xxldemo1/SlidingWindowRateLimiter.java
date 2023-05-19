package com.example.xxldemo1;

import java.util.ArrayDeque;
import java.util.Queue;

public class SlidingWindowRateLimiter {
    private final int windowSize; // 窗口大小
    private final int requestLimit; // 请求限制数
    private final long timeInterval; // 时间间隔，单位毫秒
    private Queue<Long> requestQueue; // 请求队列

    public SlidingWindowRateLimiter(int windowSize, int requestLimit, long timeInterval) {
        this.windowSize = windowSize;
        this.requestLimit = requestLimit;
        this.timeInterval = timeInterval;
        this.requestQueue = new ArrayDeque<>(windowSize);
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // 移除过期的请求记录
        while (!requestQueue.isEmpty() && currentTime - requestQueue.peek() > timeInterval) {
            requestQueue.poll();
        }

        // 判断当前请求数是否超过限制
        if (requestQueue.size() < requestLimit) {
            requestQueue.offer(currentTime);
            return true; // 允许请求
        }

        return false; // 请求超限
    }

    public static void main(String[] args) {
        // 示例用法
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(10, 5, 1000); // 窗口大小为10，每秒最多处理5个请求
        for (int i = 1; i <= 100; i++) {
            boolean allowed = rateLimiter.allowRequest();
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Rejected"));
            try {
                Thread.sleep(150); // 模拟请求间隔
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
