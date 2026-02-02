package org.example.threadPractice;

public class ThreadLifecycle {
    public static void main(String[] args) throws InterruptedException {
        Thread aThread = new A();
        System.out.println(aThread.getState());
        aThread.start();
        System.out.println(aThread.getState());
//        Thread.sleep(100);
        System.out.println(aThread.getState());
        System.out.println(Thread.currentThread().getState());

        aThread.join();
        System.out.println(aThread.getState());
    }
}

class A extends Thread{
    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}