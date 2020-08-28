
//Namespace
package com.ecommerce.oms.domain.model;

//Imports
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Class that represents and persist orders entity
 */
@Entity(name = "orders")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Order {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "order_id_generator")
    @SequenceGenerator(name = "order_id_generator", sequenceName = "order_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String reference = RandomStringUtils.randomAlphanumeric(12).toLowerCase();

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Collection<Product> products;

    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Customer customer;

    @NotNull
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "delivery_address")
    private Address deliveryAddress;

    @Min(value = 0L)
    private Double amount;
    private Integer promotion;
    private Integer status = 0;
    private Date date = new Date();


    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPromotion() {
        return promotion;
    }

    public void setPromotion(Integer promotion) {
        this.promotion = promotion;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDate() {
        if (this.date == null) {
            return null;
        }
        return new Date(this.date.getTime());
    }

    public void setDate(final Date date) {
        if (date != null) {
            this.date = new Date(date.getTime());
        }
    }
}
