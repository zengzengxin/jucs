package com.example.juc.code.productAndCummer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockAsc {


    public static void main(String[] args) {
        DataLockAsc data = new DataLockAsc();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();

    }


}


class DataLockAsc {
    public int flag = 1; //1线程1执行 //2线程2 //3线程3
    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    //生产的方法
    //判断、等待、业务、通知
    public void printA()  {
        //此处不能使用if，防止虚假唤醒
        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            System.out.println("线程执行" + Thread.currentThread().getName());
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //消费的方法
    //判断、等待、业务、通知
    public void printB()  {
        //此处不能使用if，防止虚假唤醒
        lock.lock();
        try {
            while (flag != 2) {
                condition2.await();
            }
            System.out.println("线程" + Thread.currentThread().getName());
            flag = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

    }

    public void printC()  {
        //此处不能使用if，防止虚假唤醒
        lock.lock();
        try {
            while (flag != 3) {
                condition3.await();
            }
            System.out.println("线程" + Thread.currentThread().getName());
            flag = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
