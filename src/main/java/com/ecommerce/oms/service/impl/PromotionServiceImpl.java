
//Namespace
package com.ecommerce.oms.service.impl;

//Imports
import com.ecommerce.oms.domain.model.Promotion;
import com.ecommerce.oms.exception.ResourceNotFoundException;
import com.ecommerce.oms.repository.PromotionRepository;
import com.ecommerce.oms.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Class hat handles operations regarding promotions
 */
@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private transient PromotionRepository promotionRepository;


    @Override
    public Iterable<Promotion> fetchPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion findPromotion(long id) {
        return promotionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
    }

    @Override
    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public void delete(long id) {
        promotionRepository.deleteById(id);
    }
}
