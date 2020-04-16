package com.example.juc.code.lock;

import java.time.LocalDateTime;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class useLock {
    public static void main(String[] args) {
        ticketBylock ticket = new ticketBylock();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}

    class ticketBylock{
        private  int ticket = 50;
        Lock lock = new ReentrantLock();
        public  void  sale(){
            lock.lock();
            try {
                if (ticket>0){
                    System.out.println("卖出第"+(ticket--)+"张票");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

