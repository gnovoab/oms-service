
//Namespace
package com.ecommerce.oms.domain.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class that represents a product entity
 */
public class Product {

    //Fields
    private Long id;

    @NotBlank private String sku;
    @NotBlank private String name;
    @NotNull  private Double price;
    @NotNull private Integer quantity;


    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
