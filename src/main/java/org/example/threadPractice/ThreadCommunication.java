package org.example.threadPractice;

public class ThreadCommunication {
    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();

        Thread producer = new Thread(new Producer(sharedResource));
        Thread consumer = new Thread(new Consumer(sharedResource));
        Thread producer1 = new Thread(new Producer1(sharedResource));

        producer.start();
        consumer.start();
        producer1.start();

    }
}

class SharedResource{

    public  int data;
    public boolean hasData;

    public synchronized void produce(int value){
        while (hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        data = value;
        hasData = true;
        notify();
    }

    public synchronized int consume(){
        while (!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        hasData = false;
        notify();
        return data;
    }
}

class Producer implements Runnable{

    private final SharedResource resource;

    Producer(SharedResource sharedResource){
        resource = sharedResource;
    }
    @Override
    public void run() {
        for (int i =1;i<11;i++){
            resource.produce(i);
            System.out.println(Thread.currentThread().getName()+" producer send data :"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Producer1 implements Runnable{

    private final SharedResource resource;

    Producer1(SharedResource sharedResource){
        resource = sharedResource;
    }
    @Override
    public void run() {
        for (int i =1;i<11;i++){
            resource.produce(i+1);
            System.out.println(Thread.currentThread().getName()+" producer send data :"+(i+1));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable{
    private final SharedResource resource;

    Consumer(SharedResource sharedResource){
        resource = sharedResource;
    }
    @Override
    public void run(){
        for(int i =0;i<10;i++){
            int data = resource.consume();
            System.out.println(Thread.currentThread().getName()+" consumed data :"+data);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
