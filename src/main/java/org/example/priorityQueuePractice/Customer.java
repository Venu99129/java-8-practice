package org.example.priorityQueuePractice;

//public class Customer implements Comparable<Customer>{
public class Customer {

    private int customerId;
    private String name;
    private Double price;

    public Customer(int customerId, String name, Double price) {
        this.customerId = customerId;
        this.name = name;
        this.price = price;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

//    @Override
//    public int compareTo(Customer o) {
//        return o.getCustomerId() < this.getCustomerId() ? 1 : -1;
//    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
