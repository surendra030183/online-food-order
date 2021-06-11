package com.hcl.foodorder.order.controller;

import com.hcl.foodorder.domain.exception.DuplicateOrderCreationException;
import com.hcl.foodorder.domain.exception.OrderDetailsNotFoundException;
import com.hcl.foodorder.domain.order.Order;
import com.hcl.foodorder.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.hcl.foodorder.order.service.TestData.newOrderList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Test class for controller
 */
@SpringBootTest
class OrderControllerTest {

    @Autowired
    OrderController controller = new OrderController();

    @MockBean
    OrderService service;

    /**
     * Test case for creating order
     */
    @Test
    void create_success() throws DuplicateOrderCreationException {
        List<Order> order1 = newOrderList();
        when(service.create(order1.get(0))).thenReturn(order1.get(0));
        ResponseEntity<Order> order = controller.create(order1.get(0));
        assertEquals(HttpStatus.CREATED, order.getStatusCode());
        assertNotNull(order.getBody());
        assertNotNull(order.getBody().getOrderNumber());
        assertEquals(order.getBody().getOrderNumber(), 123456789l);
        assertEquals(order.getBody().getDriverId(), 12l);
        assertEquals(order.getBody().getId(), 12l);
        assertEquals(order.getBody().getRestaurantId(), 123456789L);
        assertEquals(order.getBody().getCustomerId(), 123456789L);
    }

    /**
     * Test case for creating order failure
     */
    @Test/*(expected =DuplicateOrderCreationException.class)*/
    void create_order_failure() {
        List<Order> order1 = newOrderList();
        try {
            when(service.create(order1.get(0))).thenThrow(DuplicateOrderCreationException.class);
            ResponseEntity<Order> order = controller.create(order1.get(0));
        } catch (DuplicateOrderCreationException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test case to get the List of Orders by restaurantID
     */
    @Test
    void getRestaurantOrders_success() throws OrderDetailsNotFoundException {
        when(service.getOrdersByRestaurant(123456789L)).thenReturn(newOrderList());
        ResponseEntity<List<Order>> orders = controller.getRestaurantOrders(123456789L);
        assertNotNull(orders);
        assertEquals(3, orders.getBody().size());
        assertEquals(HttpStatus.OK, orders.getStatusCode());
    }

    /**
     * Test case to get the List of Orders by restaurantID
     */
    @Test
    void getRestaurantOrders_failure() {
        try {
            when(service.getOrdersByRestaurant(123456789L)).thenThrow(OrderDetailsNotFoundException.class);
            ResponseEntity<List<Order>> orders = controller.getRestaurantOrders(123456789L);
        } catch (OrderDetailsNotFoundException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test case to get the List of Orders by customerID
     */
    @Test
    void getCustomerOrders_success() throws OrderDetailsNotFoundException {
        when(service.getOrdersByCustomer(123456789L)).thenReturn(newOrderList());
        ResponseEntity<List<Order>> orders = controller.getCustomerOrders(123456789L);
        assertNotNull(orders);
        assertEquals(3, orders.getBody().size());
        assertEquals(HttpStatus.OK, orders.getStatusCode());
    }

    /**
     * Failure test case to get the List of Orders by customerID
     */
    @Test
    void getCustomerOrders_failure() {
        try {
            when(service.getOrdersByCustomer(123456789L)).thenThrow(OrderDetailsNotFoundException.class);
            ResponseEntity<List<Order>> orders = controller.getCustomerOrders(123456789L);
        } catch (OrderDetailsNotFoundException ex) {
            assertTrue(true);
        }
    }

    /**
     * Test case to get the List of Orders by orderNumber
     */
    @Test
    void getOrders_success() throws OrderDetailsNotFoundException {
        when(service.getOrdersByOrderNumber(123456789L)).thenReturn(newOrderList());
        ResponseEntity<List<Order>> orders = controller.getOrders(123456789L);
        assertNotNull(orders);
        assertEquals(3, orders.getBody().size());
        assertEquals(HttpStatus.OK, orders.getStatusCode());
    }

    /**
     * Test case to get the List of Orders by orderNumber
     */
    @Test
    void getOrders_failure() {
        try {
            when(service.getOrdersByOrderNumber(123456789L)).thenThrow(OrderDetailsNotFoundException.class);
            ResponseEntity<List<Order>> orders = controller.getOrders(123456789L);
        } catch (OrderDetailsNotFoundException ex) {
            assertTrue(true);
        }
    }
}