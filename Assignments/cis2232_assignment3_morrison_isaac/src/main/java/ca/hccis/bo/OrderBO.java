package ca.hccis.bo;

import ca.hccis.entity.Order;

/**
 * @author IDAM
 * @since 100226
 */
public class OrderBO {

    public double calculate(Order order) {

        double subtotal = order.getSubtotal();
        double tax = subtotal * 0.15;

        return subtotal + tax;
    }
}