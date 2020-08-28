
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

import java.util.List;


/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FetchPromotionsEndpointTest {

    //Fields
    private static final String BASE_URL = "/api/v1/promotion";


    @Autowired
    private transient TestRestTemplate restTemplate;
    
    @Autowired
    private transient PromotionRepository promotionRepository;


    @Test
    void fetchPromotionsTest() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<List<Promotion>> response = restTemplate
                .exchange(BASE_URL, HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }




    @Test
    void fetchPromotion() {
        //Prepopulate DB
        Promotion dbPromotion = promotionRepository.save(ObjectFactory.generateSamplePromotion());

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL + "/" + dbPromotion.getId(), HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(dbPromotion.getId(), response.getBody().getId());
        Assertions.assertEquals(dbPromotion.getName(), response.getBody().getName());
        Assertions.assertEquals(dbPromotion.getDescription(), response.getBody().getDescription());
        Assertions.assertEquals(dbPromotion.getDiscount(), response.getBody().getDiscount());
    }

    @Test
    void fetchPromotionWrongPayload() {
        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL + "/a", HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
}
