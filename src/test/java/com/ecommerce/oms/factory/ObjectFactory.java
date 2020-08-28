package com.ecommerce.oms.factory;

import com.ecommerce.oms.domain.model.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {


    public static Promotion generateSamplePromotion() {

        Promotion promotion = new Promotion();
        promotion.setName("TEST-" + RandomStringUtils.randomAlphabetic(8));
        promotion.setDescription(RandomStringUtils.randomAlphabetic(20));
        promotion.setDiscount(Double.parseDouble(RandomStringUtils.randomNumeric(1)));

        return promotion;
    }

    public static Order generateSampleOrder() {

        List<Product> products = new ArrayList<>();
        products.add(generateSampleProduct());
        products.add(generateSampleProduct());


        Order order = new Order();
        order.setProducts(products);
        order.setCustomer(generateSampleCustomer());
        order.setDeliveryAddress(generateSampleAddress());
        order.setAmount(Double.parseDouble(RandomStringUtils.randomNumeric(9)));

        return order;
    }




    private static Product generateSampleProduct() {
        Product product = new Product();
        product.setId(Long.parseLong(RandomStringUtils.randomNumeric(6)) * -1);
        product.setSku("TEST-" + RandomStringUtils.randomAlphanumeric(6));
        product.setName(RandomStringUtils.randomAlphabetic(6));
        product.setPrice(Double.valueOf(RandomStringUtils.randomNumeric(1, 3)));
        product.setQuantity(Integer.valueOf(RandomStringUtils.randomNumeric(1)));

        return product;
    }

    private static Customer generateSampleCustomer() {
        Customer customer = new Customer();
        customer.setId(Long.parseLong(RandomStringUtils.randomNumeric(6)) * -1);
        customer.setTitle("Mr");
        customer.setFirstName(RandomStringUtils.randomAlphabetic(6));
        customer.setLastName(RandomStringUtils.randomAlphabetic(5));
        customer.setEmail("utest_" + RandomStringUtils.randomAlphanumeric(6) + "@gmail.com");
        customer.setTelephone(RandomStringUtils.randomNumeric(11));

        return customer;
    }

    private static Address generateSampleAddress() {
        Address address = new Address();
        address.setId(Long.parseLong(RandomStringUtils.randomNumeric(6)) * -1);
        address.setLine1(RandomStringUtils.randomAlphabetic(8));
        address.setLine2(RandomStringUtils.randomAlphabetic(6));
        address.setLine3(RandomStringUtils.randomAlphabetic(4));
        address.setPostcode(RandomStringUtils.randomAlphanumeric(4) + " " + RandomStringUtils.randomAlphanumeric(3));
        address.setCity("London");
        address.setCounty("London");

        return address;
    }

}
