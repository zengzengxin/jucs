package com.example.juc.code.synchronizeds;

//线程就是线程，不要有多余的操作，解耦
public class synchronizeds {
    public static void main(String[] args) {
        ticket ticket = new ticket();
        new Thread(()->{
            for (int i = 1; i < 40 ; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i < 40 ; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i < 40 ; i++) {
                ticket.sale();
            }
        },"C").start();

    }

}

class ticket{
    private  int ticket = 50;

    public synchronized void  sale(){
        if (ticket>0){
            System.out.println("卖出第"+(ticket--)+"张票");
        }
    }
}