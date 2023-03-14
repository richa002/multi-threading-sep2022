package threadFactroies;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CustomThreadFactroyDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread.UncaughtExceptionHandler exceptionHandler = (thread,throwable)->
                System.err.println(String.format(
                "Thread %s threw exception - %s", thread.getName(),
                throwable.getMessage()));



        CustomThreadFactory factory = new CustomThreadFactory("FileDownloaderPool",false,Thread.MAX_PRIORITY,exceptionHandler);
        factory.newThread(()->
        {
            System.out.println("hello from "+Thread.currentThread().getName());
            System.out.println(2/0);
            System.out.println("code after throwing exception");
        }).start();
        Thread.sleep(2000);
        factory.newThread(()-> System.out.println("hello from "+Thread.currentThread())).start();

        factory.newThread(()-> System.out.println("hello from "+Thread.currentThread())).start();



    }
}

class CustomThreadFactory implements ThreadFactory {
    private String namePrefix;
    private Boolean daemon;
    private Integer priority;
    private Thread.UncaughtExceptionHandler exceptionHandler;
    private ThreadFactory threadFactory;
    private AtomicInteger count = new AtomicInteger(0);

    public CustomThreadFactory(String namePrefix, Boolean daemon, Integer priority, Thread.UncaughtExceptionHandler exceptionHandler) {
        this.namePrefix = namePrefix;
        this.daemon = daemon;
        this.priority = priority;
        this.exceptionHandler = exceptionHandler;
        threadFactory = Executors.defaultThreadFactory();
    }

    @Override
    public Thread newThread(Runnable r ) {
        Thread thread = threadFactory.newThread(r);
        if (namePrefix != null) {
            thread.setName(namePrefix + "-" + count.getAndIncrement());
        }
        if (daemon != null) {
            thread.setDaemon(daemon);
        }
        if (priority != null) {
            thread.setPriority(priority);
        }
        if (exceptionHandler != null) {
            thread.setUncaughtExceptionHandler(exceptionHandler);
        }
        return thread;
    }
}
