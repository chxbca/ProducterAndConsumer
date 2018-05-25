package com.company;

public class Consumer implements Runnable {
    private Buffer buffer;

    Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (Consumer.class) {
                    System.out.printf("\t\t\t%s 消费了:%2d result:%d%n", getClass().getSimpleName(), buffer.remove(), buffer.size());
                    Thread.sleep((int) (Math.random() * 10000));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
