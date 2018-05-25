package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);
        Runnable producer = new Producer(buffer);
        Runnable consumer = new Consumer(buffer);
        ExecutorService executors = Executors.newCachedThreadPool();
        executors.execute(producer);
        executors.execute(consumer);
        executors.shutdown();
    }
}
