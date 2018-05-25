package com.company;

import java.util.Random;

public class Producer implements Runnable {
    private Buffer buffer;
    private Random random = new Random();

    Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int producerNum = random.nextInt(100);
                System.out.printf("%s 生产了:%2d result:%d%n", getClass().getSimpleName(), producerNum, buffer.size());
                synchronized (Producer.class) {
                    buffer.add(producerNum);
                }
                Thread.sleep((int) (Math.random() * 10000));
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
