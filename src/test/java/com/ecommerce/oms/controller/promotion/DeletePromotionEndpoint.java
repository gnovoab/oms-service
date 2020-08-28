
//Namespace
package com.ecommerce.oms.controller.promotion;

//Imports

import com.ecommerce.oms.domain.model.Promotion;
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

import java.util.Optional;


/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeletePromotionEndpoint {

    //Fields
    private static final String BASE_URL = "/api/v1/promotion";

    @Autowired
    private transient PromotionRepository promotionRepository;

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void deletePromotionOk() {
        //Prepopulate DB
        Promotion originalPromotion = promotionRepository.save(ObjectFactory.generateSamplePromotion());

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL + "/" + originalPromotion.getId(), HttpMethod.DELETE, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());


        Optional<Promotion> deletedPromotion =  promotionRepository.findById(originalPromotion.getId());

        Assertions.assertFalse(deletedPromotion.isPresent());
    }


    @Test
    void deletePromotionWrongPayload() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL + "/a", HttpMethod.DELETE, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
