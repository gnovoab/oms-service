
//Namespace
package com.ecommerce.oms.service;


import com.ecommerce.oms.domain.model.Promotion;

public interface PromotionService {
    Iterable<Promotion> fetchPromotions();
    Promotion findPromotion(long id);
    Promotion save(Promotion promotion);
    void delete(long id);
}
