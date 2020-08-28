
//Namespace
package com.ecommerce.oms.service;


import com.ecommerce.oms.domain.model.Order;

public interface OrderService {
    Iterable<Order> fetchOrders();
    Order findOrder(long id);
    Order save(Order order);
    void delete(long id);
}
