package com.example.juc.code.single;

public class SingleModle {
    private SingleModle(){

    }

    //使用valitale的原因，
    /*
    * 因为创建singleModle的过程分为三步，1开辟空间，2构造方法初始化，3将引用赋给singleModle
    * 这三步是没有关系的，可以进行指令冲排序，如果是132的执行顺序，一旦3执行完就认为对象已经存在
    * 可能会导致一个没有初始化的对象就被返回出去，
    * 使用volatile可以避免这种情况
    *
    * */
    private static volatile SingleModle singleModle = null;

    /*
    * 为什么需要双重检（两次if）测锁，因为在第一次初始化对象的时候可能会有多个对象同时执行“singleModle == null”
    * 导致多个线程进入，如果不再多一次if判断，可能会创建多个对象
    *
    * */
    public SingleModle newInstance(){
        if (singleModle == null){
            synchronized (SingleModle.class){
                if (singleModle == null){
                    singleModle = new SingleModle();
                }
            }
        }
        return singleModle;
    }
}
