package org.example.priorityQueuePractice;

import java.util.Comparator;

public class CustomerOrder implements Comparator<Customer> {
    @Override
    public int compare(Customer o1, Customer o2) {
        return o1.getPrice() > o2.getPrice() ? 1 : -1;
    }
}
