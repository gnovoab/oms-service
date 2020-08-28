
//Namespace
package com.ecommerce.oms.domain.model;

//Imports
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Class that represents and persist promotions entity
 */
@Entity
public class Promotion {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "promotion_id_generator")
    @SequenceGenerator(name = "promotion_id_generator", sequenceName = "promotion_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank private String name;
    @NotBlank private String description;
    @NotNull  private Double discount;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
