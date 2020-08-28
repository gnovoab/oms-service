
//Namespace
package com.ecommerce.oms.controller.promotion;


//Imports
import com.ecommerce.oms.domain.model.Promotion;
import com.ecommerce.oms.factory.ObjectFactory;
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
public class CreatePromotionEndpointTest {

    //Fields
    private static final String BASE_URL = "/api/v1/promotion";

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void savePromotionOkTest() {
        //Create payload
        Promotion promotionPayload = ObjectFactory.generateSamplePromotion();

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(promotionPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<Promotion> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().getName().length() > 0);
        Assertions.assertTrue(response.getBody().getDescription().length() > 0);
        Assertions.assertTrue(response.getBody().getDiscount() > 0);
    }

    @Test
    void wrongPayload() {
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
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
