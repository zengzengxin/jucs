package com.example.juc.code.productAndCummer;

public class PcBySynchronized {
    public static void main(String[] args) {
        Data data = new Data();
        //生产者线程
        new Thread(()->{
            try {
                for (int i = 0; i <10 ; i++) {
                    data.inCrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        //消费者线程
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

/*
* 资源类，用于提供资源，也就是变量+需要的方法（data和生产的方法，消费的方法）
* */
class Data {
    public int data = 0;

    //生产的方法
    //判断、等待、业务、通知
    public synchronized void inCrement() throws InterruptedException {
        //此处不能使用if，防止虚假唤醒
        while (data != 0) {
            this.wait();
        }
        data++;
        System.out.println("线程生产" + Thread.currentThread().getName() + "==>" + data);
        this.notifyAll();

    }

    //消费的方法
    //判断、等待、业务、通知
    public synchronized void deCrement() throws InterruptedException {
        //此处不能使用if，防止虚假唤醒
        while (data == 0) {
            this.wait();
        }
        data--;
        System.out.println("线程消费" + Thread.currentThread().getName() + "==>" + data);
        this.notifyAll();
    }
}


