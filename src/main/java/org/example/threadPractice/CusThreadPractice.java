package org.example.threadPractice;

public class CusThreadPractice {

    public static void main(String[] args) {

        // creating threads in java

        // using thread class
        Cthread cthread = new Cthread();
        cthread.start();

        // using runnable
        Thread athread = new Thread(new AThread());
        athread.start();
    }
}


class Cthread extends Thread{
    @Override
    public void run() {
        System.out.println("creating thread using thread class");
    }
}

class AThread implements Runnable{
    @Override
    public void run() {
        System.out.println("creating thread using runnable interface");
    }
}