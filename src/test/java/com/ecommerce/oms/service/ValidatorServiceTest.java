package com.ecommerce.oms.service;

import com.ecommerce.oms.domain.model.Customer;
import com.ecommerce.oms.exception.ValidationException;
import com.ecommerce.oms.factory.ObjectFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidatorServiceTest {

    //Fields
    @Autowired
    private transient ValidatorService validatorService;

    @Test
    public void validateTest() {
        validatorService.validate(ObjectFactory.generateSampleOrder().getCustomer());
    }

    @Test
    public void validateFailedTest() {
        Customer customer = ObjectFactory.generateSampleOrder().getCustomer();
        customer.setEmail("This is a wrong email");
        Assertions.assertThrows(ValidationException.class, () -> {
            validatorService.validate(customer);
        });

    }

}
