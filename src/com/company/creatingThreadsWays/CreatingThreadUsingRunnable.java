package com.company.creatingThreadsWays;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CreatingThreadUsingRunnable {



    public static void main(String[] args) throws InterruptedException {


        Runnable r = ()-> System.out.println("hi");
        Executor e;
        ExecutorService es;


	    // write your code here
        System.out.println("in main thread - entry: " +Thread.currentThread());



// new Thread(r);
        Runnable runnable = () -> {
            System.out.println("started first thread");
            System.out.println(Thread.currentThread());

        };

        Runnable runnable2 = ()-> System.out.println("hello");
// service 1 // itemid
        // service 2 // orderid
        Callable<String> callable = ()-> "richa";// calling first remote service
        Callable<String> callable2 = ()-> "abc";// calling first remote service
        Callable<String> callable3 = ()-> "abc";// calling first remote service
        Callable<String> callable4 = ()-> "abc";// calling first remote service
        List<Callable<String>> listOfTasks = new ArrayList<>();


        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> answers = new ArrayList<>();

        for(Callable<String> task : listOfTasks){
            Future<String> submit = executorService.submit(task);
            answers.add(submit);
        }
        // code
        for(Future<String> answer :answers){
            try {
                answer.get(550,TimeUnit.MILLISECONDS);
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            } catch (TimeoutException ex) {
                ex.printStackTrace();
            }
        }

        Stream<Callable<String>> stream = listOfTasks.stream();
        Stream<Future<String>> futureStream = stream.map(task -> executorService.submit(task));
        List<Future<String>> collect = futureStream.collect(Collectors.toList());

        Future<String> submit = executorService.submit(callable);
        Future<String> submit2 = executorService.submit(callable2);
        // execute relevant
        try {

            String s = submit.get(1000, TimeUnit.MILLISECONDS); // blocking
            String s1 = submit2.get();
        } catch (ExecutionException | TimeoutException ex) {
            ex.printStackTrace();
        }

        //



        Thread t1 = new Thread(runnable);


       Runnable runnable1 = () -> {
            System.out.println(2/0);
            System.out.println("started second thread");
            System.out.println(Thread.currentThread());

        };
       // new Thread(new ThreadGroup("Electricity"), runnable,"Worker-0").start();

        new Thread(new ThreadGroup("Electricity"), runnable1,"Worker-1").start();

        Thread.sleep(1000);
        System.out.println("exiting main thread: "+Thread.currentThread());

    }
}
