package com.hcl.foodorder.order.service;

import com.hcl.foodorder.domain.order.Order;
import com.hcl.foodorder.domain.order.OrderStatus;
import com.hcl.foodorder.domain.restaurant.MenuItem;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This represents the test data required for testing
 */
@Component
public class TestData {

    public static List<Order> newOrderList() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setCreatedDate(new Date());
        order.setLastUpdatedDate(new Date());
        order.setOrderNumber(123456789l);
        order.setCustomerId(123456789l);
        order.setDriverId(12l);
        order.setId(12l);
        order.setRestaurantId(123456789l);
        order.setStatus(OrderStatus.CREATED);
        order.setItemList(newMenuItems());
        orders.add(order);
        Order order1 = new Order();
        order1.setCreatedDate(new Date());
        order1.setLastUpdatedDate(new Date());
        order1.setOrderNumber(123456789L);
        order1.setCustomerId(102345678L);
        order1.setDriverId(12l);
        order1.setId(12l);
        order1.setRestaurantId(123456789l);
        order1.setStatus(OrderStatus.CREATED);
        order1.setItemList(newMenuItems());
        orders.add(order);
        orders.add(order1);
        return orders;
    }

    private static Set<MenuItem> newMenuItems() {
        Set<MenuItem> items = new HashSet<>();
        MenuItem item = new MenuItem();
        item.setId(12l);
        item.setRestaurantId(12l);
        items.add(item);
        return items;
    }
}
