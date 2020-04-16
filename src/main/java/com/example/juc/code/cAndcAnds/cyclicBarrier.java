package com.example.juc.code.cAndcAnds;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
cyclicBarrier:它是加法计数器，当加到预设的值得时候就会自动开始执行之后的值
实现集齐七龙珠召唤神龙的功能
* */
public class cyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐七龙珠召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                System.out.println("找到"+Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                }
            },"第"+i+"龙珠").start();
        }

    }
}
