package org.example.threadPractice;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

//
//        long startTime = System.currentTimeMillis();
//
//        Thread[] tpool = new Thread[3];
//        for(int i =1;i<10;i++){
//            int finalI = i;
//             tpool[i-1] = new Thread(){
//                @Override
//                public void run(){
//                    System.out.println(factorial(finalI));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            };
//
//            tpool[i-1].start();
//        }
//        for(Thread th : tpool){
//            th.join();
//        }
//        System.out.println("time taking to complete the task :"+(System.currentTimeMillis()-startTime));

        //// thread pool consecpt with Executorservice

//        long startTime = System.currentTimeMillis();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        for (int i = 1; i < 10; i++) {
//            int finalI = i;
//            executorService.submit(() -> {
//                System.out.println(factorial(finalI));
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        }
//
//        executorService.shutdown();
//
//        System.out.println("time taking to complete the task :" + (System.currentTimeMillis() - startTime));


        //// executor service with latch concept

//        int noOfThreads = 3;
//        ExecutorService ex = Executors.newFixedThreadPool(noOfThreads);
//        CountDownLatch latch = new CountDownLatch(noOfThreads);
//        Future<String> future1  = ex.submit(new DependedService(latch));
//        Future<String> future2 = ex.submit(new DependedService(latch));
//        Future<String> future3 = ex.submit(new DependedService(latch));
//
//       latch.await(2, TimeUnit.SECONDS);
//        latch.await();
//
//        System.out.println("All depended services are finished. starting main thread service....");
//        ex.shutdown();

        //threadpool with cyclic service

        int numberOfSubSystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubSystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("All systems are up and running. system startup is complete.");
            }

        });

        Thread webserverThread = new Thread(new SubSystem("webServer",2000,barrier));
        Thread databaseThread = new Thread(new SubSystem("database",4000,barrier));
        Thread cacheThread = new Thread(new SubSystem("cache",3000,barrier));
        Thread messagingServiceThread = new Thread(new SubSystem("messaging service",5000,barrier));

        webserverThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();

    }

    public static long factorial(long num) {
        long result = num;
        for (int i = 2; i < num; i++) {
            result *= i;
        }

        return result;
    }
}

//class DependedService implements Callable<String>{
//
//    private final CountDownLatch latch ;
//
//    DependedService(CountDownLatch latch){
//        this.latch = latch;
//    }
//
//    @Override
//    public String call() throws Exception {
//
//        String name = Thread.currentThread().getName();
//        Thread.sleep(4000);
//        System.out.println(name +" is running task");
//        latch.countDown();
//        return "ok";
//    }
//}

class SubSystem implements Runnable {
    private final String name;
    private final CyclicBarrier barrier;
    private final int initializationTime;

    SubSystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime);
            System.out.println(name + " initialization completed.");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
    }
}



