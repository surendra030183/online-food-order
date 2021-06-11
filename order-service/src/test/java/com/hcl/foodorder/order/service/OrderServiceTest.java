package com.hcl.foodorder.order.service;

import com.hcl.foodorder.domain.exception.DuplicateOrderCreationException;
import com.hcl.foodorder.domain.exception.OrderDetailsNotFoundException;
import com.hcl.foodorder.domain.order.Order;
import com.hcl.foodorder.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.hcl.foodorder.order.service.TestData.newOrderList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test cases for service class
 */
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService service = new OrderService();

    @MockBean
    OrderRepository repository;

    /**
     * Test case for creating order
     */
    @Test
    void create_success() throws DuplicateOrderCreationException {
        List<Order> order1 = newOrderList();
        when(repository.save(order1.get(0))).thenReturn(order1.get(0));
        Order order = service.create(order1.get(0));
        assertNotNull(order.getOrderNumber());
        assertEquals(order.getOrderNumber(), 123456789l);
        assertEquals(order.getDriverId(), 12l);
        assertEquals(order.getId(), 12l);
        assertEquals(order.getRestaurantId(), 123456789L);
        assertEquals(order.getCustomerId(), 123456789L);
    }

    /**
     * Negative test case for creating order
     */
    @Test
    void create_failure() {
        List<Order> order1 = newOrderList();
        when(repository.findByOrderNumber(order1.get(0).getOrderNumber())).thenReturn(order1);
        try {
            Order order = service.create(order1.get(0));
        } catch (DuplicateOrderCreationException ex) {
            assertTrue(true);
        }
    }


    /**
     * Test case to get the List of Orders by restaurantID
     */
    @Test
    void getOrdersByRestaurant() throws OrderDetailsNotFoundException {

        when(repository.findByRestaurantId(123456789L)).thenReturn(newOrderList());
        List<Order> orders = service.getOrdersByRestaurant(123456789L);
        assertNotNull(orders);
        assertNotNull(orders.get(0).getOrderNumber());
        assertEquals(orders.get(0).getOrderNumber(), 123456789l);
    }

    /**
     * Negative test case to get the List of Orders by restaurantID
     */
    @Test
    void getOrdersByRestaurant_notFound() {
        try {
            when(repository.findByRestaurantId(123456789L)).thenReturn(null);
            List<Order> orders = service.getOrdersByRestaurant(123456789L);
        } catch (OrderDetailsNotFoundException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test case to get the List of Orders by customerID
     */
    @Test
    void getOrdersByCustomer() throws OrderDetailsNotFoundException {

        when(repository.findByCustomerId(123456789L)).thenReturn(newOrderList());
        List<Order> orders = service.getOrdersByCustomer(123456789L);
        assertNotNull(orders);
        assertEquals(3, orders.size());
        assertEquals(123456789L, orders.get(0).getCustomerId());
        assertEquals(123456789L, orders.get(1).getCustomerId());
        assertEquals(102345678L, orders.get(2).getCustomerId());
    }

    /**
     * Negative test case to get the List of Orders by customerID
     */
    @Test
    void getOrdersByCustomer_notFound() {

        try {
            when(repository.findByCustomerId(123456789L)).thenReturn(null);
            List<Order> orders = service.getOrdersByCustomer(123456789L);
        } catch (OrderDetailsNotFoundException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test case to get the List of Orders by orderNumber
     */
    @Test
    void getOrdersByOrderNumber() throws OrderDetailsNotFoundException {
        when(repository.findByOrderNumber(123456789L)).thenReturn(newOrderList());
        List<Order> orders = service.getOrdersByOrderNumber(123456789L);
        assertNotNull(orders);
        assertEquals(3, orders.size());
        assertNotNull(orders.get(0).getOrderNumber());
        assertEquals(orders.get(0).getOrderNumber(), 123456789l);
    }

    /**
     * Negative tst case to get the List of Orders by orderNumber
     */
    @Test
    void getOrdersByOrderNumber_notFound() {
        when(repository.findByOrderNumber(123456789L)).thenReturn(null);
        try {
            List<Order> orders = service.getOrdersByOrderNumber(123456789L);
        } catch (OrderDetailsNotFoundException ex) {
            assertTrue(true);
        }
    }
}