
//Namespace
package com.ecommerce.oms.repository;

//Imports
import com.ecommerce.oms.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
