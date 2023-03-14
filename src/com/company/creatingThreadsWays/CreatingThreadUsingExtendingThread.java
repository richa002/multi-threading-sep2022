package com.company.creatingThreadsWays;

public class CreatingThreadUsingExtendingThread {
    public static void main(String[] args) {

        System.out.println("Hello from "+ Thread.currentThread());
        new MyThread().start();

       //run() or start()
        System.out.println("Bye from "+ Thread.currentThread());



    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("Hello from "+Thread.currentThread());
        System.out.println("this is  a task running in new thread");
    }
}
