package com.example.productcatalogueproxy.Models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)

    private Category category;
    private String image;
    //private String category;

}
