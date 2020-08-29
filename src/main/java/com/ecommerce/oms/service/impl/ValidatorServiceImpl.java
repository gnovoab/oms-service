
//Namespace
package com.ecommerce.oms.service.impl;


import com.ecommerce.oms.exception.ValidationException;
import com.ecommerce.oms.service.ParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecommerce.oms.service.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Class that handles calidation operations
 */
@Service
public class ValidatorServiceImpl implements ValidatorService {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorServiceImpl.class);

    //Fields
    @Autowired
    private transient ParsingService parsingService;

    private transient ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private transient Validator validator = factory.getValidator();


    @Override
    @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
    public void validate(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Object> violation : violations) {
                sb.append(violation.getPropertyPath().toString() + " " + violation.getMessage());
                sb.append(" ; ");
            }
            LOGGER.error("ValidationException while validating pojo[{}] [{}] [{}]", object.getClass().getName(), parsingService.objToJson(object),  sb.toString());
            throw new ValidationException("Bad Request: [" + sb.toString() + "]");

        }
    }
}

