package ca.hccis.bo;

import ca.hccis.entity.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Isaac Morrison
 * @since 2026-10-03
 */
public class OrderBOTest {

    @Test
    void testCalculateOneSandwich() {

        Order order = new Order(1);
        order.addSandwich("Classic Italian", 10.00);

        OrderBO orderBO = new OrderBO();

        double result = orderBO.calculate(order);

        assertEquals(11.50, result, 0.001);
    }

    @Test
    void testCalculateMultipleSandwiches() {

        Order order = new Order(2);

        order.addSandwich("Classic Italian", 10.00);
        order.addSandwich("Chicken Pesto", 15.00);

        OrderBO orderBO = new OrderBO();

        double result = orderBO.calculate(order);

        assertEquals(28.75, result, 0.001);
    }

    @Test
    void testCalculateTotalGreaterThanSubtotal() {

        Order order = new Order(3);

        order.addSandwich("Meatball", 15.00);

        OrderBO orderBO = new OrderBO();

        double result = orderBO.calculate(order);

        assertTrue(result > order.getSubtotal());
    }
}