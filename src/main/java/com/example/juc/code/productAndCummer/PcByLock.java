package com.example.juc.code.productAndCummer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PcByLock {
    public static void main(String[] args) {
        DataLock data = new DataLock();

        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.inCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.deCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}

class DataLock {
    public int data = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //生产的方法
    //判断、等待、业务、通知
    public  void inCrement() throws InterruptedException {
        //此处不能使用if，防止虚假唤醒
        lock.lock();
        try {
            while (data != 0){
                condition.await();
            }
            data++;
            System.out.println("线程生产" + Thread.currentThread().getName() + "==>" + data);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //消费的方法
    //判断、等待、业务、通知
    public  void deCrement() throws InterruptedException {
        //此处不能使用if，防止虚假唤醒
        lock.lock();
        try {
            while (data ==0){
                condition.await();
            }
            data--;
            System.out.println("线程消费" + Thread.currentThread().getName() + "==>" + data);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
