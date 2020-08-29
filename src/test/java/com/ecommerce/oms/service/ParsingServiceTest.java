
//Namespace
package com.ecommerce.oms.service;

import com.ecommerce.oms.domain.model.Promotion;
import com.ecommerce.oms.factory.ObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParsingServiceTest {

    //Fields
    @Autowired
    private transient ParsingService parsingService;

    @Test
    public void objToJsonTest() {
        Promotion promotion = ObjectFactory.generateSamplePromotion();
        String json = (String) parsingService.objToJson(promotion);
        Assertions.assertTrue(json.length() > 0);
    }

    @Test
    public void jsonToObjTest() {

        Promotion promotion = ObjectFactory.generateSamplePromotion();

        String json = (String) parsingService.objToJson(promotion);
        Promotion promotionParsed = (Promotion) parsingService.jsonToObj(json, Promotion.class);

        //Verify
        Assertions.assertNotNull(promotionParsed);
        Assertions.assertEquals(promotion.getName(), promotionParsed.getName());
        Assertions.assertEquals(promotion.getDescription(), promotionParsed.getDescription());
        Assertions.assertEquals(promotion.getDiscount(), promotionParsed.getDiscount());
    }
}
