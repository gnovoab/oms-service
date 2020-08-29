
//Namespace
package com.ecommerce.oms.service.impl;

//Imports
import com.ecommerce.oms.exception.ParseException;
import com.ecommerce.oms.service.ParsingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class ParsingServiceImpl implements ParsingService {


    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(ParsingServiceImpl.class);

    //Fields
    private transient ObjectMapper objectMapper = new ObjectMapper();


    /**
     * Cast Json to an Object.
     * @param json
     * @param aClass
     * @return
     */
    @Override
    public Object jsonToObj(String json, Class aClass) {
        try {
            return objectMapper.readValue(json, aClass);
        }
        catch (IOException e) {
            LOGGER.error("IOException while converting JSON [{}] into Pojo [{}]", json, aClass);
            throw new ParseException("IOException while converting Pojo JSON [" + json + "] into Pojo [" + aClass + "]", e);
        }
        catch (Exception e) {
            LOGGER.error("Exception while converting JSON [{}] into Pojo [{}]", json, aClass);
            throw new ParseException("Exception while converting Pojo JSON [" + json + "] into Pojo [" + aClass + "]", e);
        }

    }


    /**
     * Transform an Object to JSON
     * @param obj
     * @return
     */
    @Override
    public Object objToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            LOGGER.error("JsonProcessingException while parsing Pojo [{}]", obj.getClass());
            throw new ParseException("JsonProcessingException while parsing Pojo [" + obj.getClass() + "]", e);
        }
        catch (Exception e) {
            LOGGER.error("Exception while parsing Pojo [{}]", obj.getClass());
            throw new ParseException("Exception while parsing Pojo [" + obj.getClass() + "]", e);
        }
    }
}
