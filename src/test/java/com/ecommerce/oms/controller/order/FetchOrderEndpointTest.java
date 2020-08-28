
//Namespace
package com.ecommerce.oms.controller.order;

//Imports
import com.ecommerce.oms.domain.model.Order;
import com.ecommerce.oms.factory.ObjectFactory;
import com.ecommerce.oms.repository.OrderRepository;
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
public class FetchOrderEndpointTest {

    //Fields
    private static final String BASE_URL = "/api/v1/order";


    @Autowired
    private transient TestRestTemplate restTemplate;
    
    @Autowired
    private transient OrderRepository orderRepository;


    @Test
    void fetchOrdersTest() {

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<List<Order>> response = restTemplate
                .exchange(BASE_URL, HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }



    @Test
    void fetchOrder() {
        //Prepopulate DB
        Order dbOrder = orderRepository.save(ObjectFactory.generateSampleOrder());

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(requestHeaders);

        //Invoke the API service
        ResponseEntity<Order> response = restTemplate
                .exchange(BASE_URL + "/" + dbOrder.getId(), HttpMethod.GET, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(dbOrder.getId(), response.getBody().getId());
        Assertions.assertEquals(dbOrder.getReference(), response.getBody().getReference());
        Assertions.assertEquals(dbOrder.getAmount(), response.getBody().getAmount());
        Assertions.assertEquals(dbOrder.getDate(), response.getBody().getDate());
        Assertions.assertEquals(dbOrder.getStatus(), response.getBody().getStatus());

        Assertions.assertEquals(dbOrder.getProducts().size(), response.getBody().getProducts().size());

        Assertions.assertEquals(dbOrder.getCustomer().getId(), response.getBody().getCustomer().getId());
        Assertions.assertEquals(dbOrder.getCustomer().getTitle(), response.getBody().getCustomer().getTitle());
        Assertions.assertEquals(dbOrder.getCustomer().getFirstName(), response.getBody().getCustomer().getFirstName());
        Assertions.assertEquals(dbOrder.getCustomer().getLastName(), response.getBody().getCustomer().getLastName());
        Assertions.assertEquals(dbOrder.getCustomer().getEmail(), response.getBody().getCustomer().getEmail());
        Assertions.assertEquals(dbOrder.getCustomer().getTelephone(), response.getBody().getCustomer().getTelephone());

        Assertions.assertEquals(dbOrder.getDeliveryAddress().getId(), response.getBody().getDeliveryAddress().getId());
        Assertions.assertEquals(dbOrder.getDeliveryAddress().getLine1(), response.getBody().getDeliveryAddress().getLine1());
        Assertions.assertEquals(dbOrder.getDeliveryAddress().getLine2(), response.getBody().getDeliveryAddress().getLine2());
        Assertions.assertEquals(dbOrder.getDeliveryAddress().getLine3(), response.getBody().getDeliveryAddress().getLine3());
        Assertions.assertEquals(dbOrder.getDeliveryAddress().getPostcode(), response.getBody().getDeliveryAddress().getPostcode());
        Assertions.assertEquals(dbOrder.getDeliveryAddress().getCity(), response.getBody().getDeliveryAddress().getCity());
        Assertions.assertEquals(dbOrder.getDeliveryAddress().getCounty(), response.getBody().getDeliveryAddress().getCounty());

    }

}
