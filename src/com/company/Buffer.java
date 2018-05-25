package com.company;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buffer {
    private final int capacity;
    private LinkedList<Integer> queue = new LinkedList<>();

    private static Lock lock = new ReentrantLock();

    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();

    Buffer(int capacity) {
        this.capacity = capacity;
    }

    void add(int value) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }
            queue.offer(value);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    int remove() {
        int value = 0;
        lock.lock();
        //noinspection finally
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            value = queue.remove();
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return value;
    }

    int size() {
        return queue.size();
    }
}
