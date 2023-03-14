package com.company.creatingThreadsWays;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CreatingThreadUsingThreadFactory {

    public static void main(String[] args) {

        MyThreadFactory factory = new MyThreadFactory("Plumbing");
        factory.newThread(() ->
                System.out.println("Hello from.. " + Thread.currentThread())).start();
        factory.newThread(() ->
                System.out.println("Hello from.. " + Thread.currentThread())).start();

        MyThreadFactory factory2 = new MyThreadFactory("Electricity");
        factory2.newThread(() ->
                System.out.println("Hello from.. " + Thread.currentThread())).start();
        factory2.newThread(() ->
                System.out.println("Hello from.. " + Thread.currentThread())).start();


    }
}

 class MyThreadFactory implements ThreadFactory{
    private AtomicInteger counter= new AtomicInteger(0);
    private String group;

    public MyThreadFactory(String group){
        this.group = group;
    }
    @Override
    public Thread newThread(Runnable r) {

     //  return new Thread(r);
        return new Thread(new ThreadGroup(group),r,"Worker-"+(counter.incrementAndGet())); // can create custom threads
    }
}
