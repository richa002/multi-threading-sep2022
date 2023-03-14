package threadFactroies;

import java.util.concurrent.Executors;

public class ThreadFactoryDemo {
    public static void main(String[] args) {
//        Runnable r = ()-> System.out.println("Hello from "+Thread.currentThread().getName());
//        ThreadFactory threadFactory1 = Executors.defaultThreadFactory();
//
//        threadFactory1.newThread(r).start();
//        threadFactory1.newThread(r).start();
//        threadFactory1.newThread(r).start();
//        System.out.println("***********************");
//        Executors.defaultThreadFactory().newThread(r).start();
//        Executors.defaultThreadFactory().newThread(r).start();

// PROBELM WITH DEFAULT THREAD FACTORY
// IF ANY EXCEPTION OCCURS WITH DEFAULT THREAD FACTORY, IT THROWS EXCEPTION AND THE THREAD TERMINATES
        Executors.defaultThreadFactory().newThread(()->
        {
            System.out.println("hello from "+Thread.currentThread().getName());
            System.out.println(2/0);
            System.out.println("code after throwing exception");
        }).start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Executors.defaultThreadFactory().newThread(()->
        {
            System.out.println("hello from "+Thread.currentThread().getName());
            System.out.println(2/0);
            System.out.println("code after throwing exception");
        }).start();
    }
}
