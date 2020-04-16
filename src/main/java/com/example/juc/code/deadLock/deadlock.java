package com.example.juc.code.deadLock;

public class deadlock {
    public static void main(String[] args) {
        //先获取黑再获取白
        new Thread(new chopsticks("black","white")).start();
        //先获取白再获取黑
        new Thread(new chopsticks("white","black")).start();
    }
}

class chopsticks implements Runnable{
    //两个筷子，一黑一白，必须都获取才可以吃饭
     private  String chopsticks1 ;
     private  String chopsticks2 ;


    public chopsticks(String chopsticks1, String chopsticks2) {
        this.chopsticks1 = chopsticks1;
        this.chopsticks2 = chopsticks2;
    }

    public  void run() {
        synchronized (chopsticks1){
            System.out.println(Thread.currentThread().getName()+"获得"+chopsticks1+"筷子");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
            synchronized(chopsticks2){
                System.out.println(Thread.currentThread().getName()+"获得"+chopsticks2+"筷子");
            };
        }
    }




}