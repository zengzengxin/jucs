package com.example.juc.code.cAndcAnds;

import java.util.concurrent.CountDownLatch;

/*
* 实现看门狗功能，所有人离开后才能关门
* 假设有六个人，六个人离开后就关门
* */
public class counDownLunchtest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"离开教室");
                countDownLatch.countDown();
            },"我是"+temp+"号同学").start();
        }
        countDownLatch.await();
        System.out.println("所有人都已经离开，开始关门");
    }

}

