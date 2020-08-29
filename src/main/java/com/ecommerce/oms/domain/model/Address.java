
//Namespace
package com.ecommerce.oms.domain.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Class that represents address entity
 */
public class Address {

    //Fields
    private Long id;
    @NotBlank private String line1;
    @NotBlank private String line2;
    @NotBlank private String line3;

    @Size(min = 6, max = 8)
    @NotBlank
    private String postcode;

    @NotBlank private String county;
    @NotBlank private String city;


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
