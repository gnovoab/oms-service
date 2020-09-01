
//Namespace
package com.ecommerce.oms.repository;

//Imports
import com.ecommerce.oms.domain.model.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion, Long> {
}
