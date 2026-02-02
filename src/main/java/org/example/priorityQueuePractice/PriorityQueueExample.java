package org.example.priorityQueuePractice;

import java.util.AbstractQueue;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
//        Queue<Integer> queue = new PriorityQueue<>();
//
//        queue.add(10);
//        queue.add(1);
//        queue.add(3);
//        queue.add(6);
//
//        System.out.println(queue);
//        System.out.println(queue.poll());

        Queue<Customer> queue = new PriorityQueue<>(new CustomerOrder());
        Customer cus = new Customer(1,"balu",100.00);
        Customer cus1 = new Customer(10,"panner",900.00 );
        Customer cus2 = new Customer(20, "kewee", 800.00);

        queue.add(cus);
        queue.add(cus1);
        queue.add(cus2);

        System.out.println(queue);
        System.out.println(queue.poll());
    }
}
