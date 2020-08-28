
//Namespace
package com.ecommerce.oms.controller.promotion;

//Imports
import com.ecommerce.oms.domain.model.Promotion;
import com.ecommerce.oms.exception.ResourceNotFoundException;
import com.ecommerce.oms.factory.ObjectFactory;
import com.ecommerce.oms.repository.PromotionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;


/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdatePromotionEndpoint {

    //Fields
    private static final String BASE_URL = "/api/v1/promotion";

    @Autowired
    private transient PromotionRepository promotionRepository;

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void updatePromotionOk() {
        //Prepopulate DB
        Promotion originalPromotion = promotionRepository.save(ObjectFactory.generateSamplePromotion());

        //Create payload
        Promotion promotionPayload = ObjectFactory.generateSamplePromotion();

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(promotionPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL + "/" + originalPromotion.getId(), HttpMethod.PUT, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Promotion updatedPromotion =  promotionRepository
                .findById(originalPromotion.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

        Assertions.assertEquals(originalPromotion.getId(), updatedPromotion.getId());
        Assertions.assertNotEquals(originalPromotion.getName(), updatedPromotion.getName());
        Assertions.assertNotEquals(originalPromotion.getDescription(), updatedPromotion.getDescription());
        Assertions.assertNotEquals(originalPromotion.getDiscount(), updatedPromotion.getDiscount());
    }


    @Test
    void updatePromotionWrongPayload() {
        //Create payload
        Promotion promotionPayload = ObjectFactory.generateSamplePromotion();
        promotionPayload.setName(null);

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(promotionPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL + "/-1", HttpMethod.PUT, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        //Invoke the API service
        response = restTemplate.exchange(BASE_URL + "/a", HttpMethod.PUT, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
