package com.example.juc.code.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //资源对象
        Data data = new Data();
        //用futerTask封装data
        FutureTask futureTask = new FutureTask(data);
       // FutureTask futureTask2 = new FutureTask(data);
        //启动线程调用call
        new Thread(futureTask,"A").start();

        //获取返回值

        System.out.println(futureTask.get());
        new Thread(futureTask,"B").start();
        System.out.println(futureTask.get());

    }

}

//资源类
class Data implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("我是call");
        return Thread.currentThread().getName()+"call";
    }
}