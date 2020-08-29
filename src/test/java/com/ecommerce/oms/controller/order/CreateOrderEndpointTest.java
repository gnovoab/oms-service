
//Namespace
package com.ecommerce.oms.controller.order;


//Imports
import com.ecommerce.oms.domain.api.ApiErrorResponse;
import com.ecommerce.oms.domain.model.Order;
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
public class CreateOrderEndpointTest {

    //Fields
    private static final String BASE_URL = "/api/v1/order";

    @Autowired
    private transient TestRestTemplate restTemplate;

    @Test
    void saveOrderOkTest() {
        //Create payload
        Order orderPayload = ObjectFactory.generateSampleOrder();

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(orderPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<Order> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().getId() > 0);
        Assertions.assertEquals(12, response.getBody().getReference().length());
        Assertions.assertEquals(0, response.getBody().getStatus());
        Assertions.assertTrue(response.getBody().getAmount() > 0);

        Assertions.assertTrue(response.getBody().getProducts().size() > 0);
        Assertions.assertTrue(response.getBody().getProducts().stream().findFirst().get().getId() < 0);
        Assertions.assertTrue(response.getBody().getProducts().stream().findFirst().get().getSku().length() > 0);
        Assertions.assertTrue(response.getBody().getProducts().stream().findFirst().get().getName().length() > 0);
        Assertions.assertTrue(response.getBody().getProducts().stream().findFirst().get().getQuantity() > 0);
        Assertions.assertTrue(response.getBody().getProducts().stream().findFirst().get().getPrice() > 0);

        Assertions.assertTrue(response.getBody().getCustomer().getId() < 0);
        Assertions.assertTrue(response.getBody().getCustomer().getTitle().length() > 0);
        Assertions.assertTrue(response.getBody().getCustomer().getFirstName().length() > 0);
        Assertions.assertTrue(response.getBody().getCustomer().getLastName().length() > 0);
        Assertions.assertTrue(response.getBody().getCustomer().getEmail().length() > 0);
        Assertions.assertTrue(response.getBody().getCustomer().getTelephone().length() > 0);

        Assertions.assertTrue(response.getBody().getDeliveryAddress().getId() < 0);
        Assertions.assertTrue(response.getBody().getDeliveryAddress().getLine1().length() > 0);
        Assertions.assertTrue(response.getBody().getDeliveryAddress().getLine2().length() > 0);
        Assertions.assertTrue(response.getBody().getDeliveryAddress().getLine3().length() > 0);
        Assertions.assertTrue(response.getBody().getDeliveryAddress().getPostcode().length() > 0);
        Assertions.assertTrue(response.getBody().getDeliveryAddress().getCity().length() > 0);
        Assertions.assertTrue(response.getBody().getDeliveryAddress().getCounty().length() > 0);
    }

    @Test
    void wrongPayload() {
        //Create payload
        Order orderPayload = ObjectFactory.generateSampleOrder();
        orderPayload.setAmount(-1.0);

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(orderPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<Object> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());


        //Validate Customer
        orderPayload = ObjectFactory.generateSampleOrder();
        orderPayload.getCustomer().setEmail("this is wrong email");
        request = new HttpEntity<>(orderPayload, requestHeaders);
        response = restTemplate.exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        //Validate Address
        orderPayload = ObjectFactory.generateSampleOrder();
        orderPayload.getDeliveryAddress().setLine1("");
        request = new HttpEntity<>(orderPayload, requestHeaders);
        response = restTemplate.exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        //Validate Products
        orderPayload = ObjectFactory.generateSampleOrder();
        orderPayload.getProducts().stream().findFirst().get().setName("");
        request = new HttpEntity<>(orderPayload, requestHeaders);
        response = restTemplate.exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
