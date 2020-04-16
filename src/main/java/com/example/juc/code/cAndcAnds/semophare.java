package com.example.juc.code.cAndcAnds;

import java.util.concurrent.Semaphore;

/*
* semophare:是信号量，通过信号量控制同时执行的线程，起到限流的作用
*
* 实现停车功能，只有三个车位，每辆车停车两秒，6辆车停车的控住程序
* */
public class semophare {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"停车");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"离开");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            },"我是第"+i+"车").start();
        }
    }
}
