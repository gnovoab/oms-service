package com.ecommerce.oms.service;

public interface ParsingService {
    Object objToJson(Object obj);
    Object jsonToObj(String json, Class aClass);
}
