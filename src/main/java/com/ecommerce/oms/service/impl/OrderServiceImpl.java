
//Namespace
package com.ecommerce.oms.service.impl;

//Imports
import com.ecommerce.oms.domain.model.Order;
import com.ecommerce.oms.exception.ResourceNotFoundException;
import com.ecommerce.oms.repository.OrderRepository;
import com.ecommerce.oms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Class hat handles operations regarding orders
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private transient OrderRepository orderRepository;


    @Override
    public Iterable<Order> fetchOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrder(long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public Order save(Order orders) {
        return orderRepository.save(orders);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }
}
