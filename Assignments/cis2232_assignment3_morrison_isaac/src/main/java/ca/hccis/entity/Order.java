package ca.hccis.entity;

import java.util.ArrayList;

/**
 * @author idam
 * @since 092526
 */

public class Order {

    private int orderNum;
    private ArrayList<String> sandwiches;
    private double subtotal;
    private double tax;
    private double total;

    public Order(int orderNum) {
        this.orderNum = orderNum;
        this.sandwiches = new ArrayList<>();
        this.subtotal = 0;
        this.tax = 0;
        this.total = 0;
    }

    public void addSandwich(String sandwich, double price) {
        sandwiches.add(sandwich);
        //I left this to make sure I wouldn't break the code
        // but if you check the tests, they refer to OrderBO and not Order
        subtotal += price;
        tax = subtotal * 0.15;
        total = subtotal + tax;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public ArrayList<String> getSandwiches() {
        return sandwiches;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }
}
