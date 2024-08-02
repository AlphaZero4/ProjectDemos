package com.example.productcatalogueproxy.FakeStoreDtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FakeProductDto implements Serializable {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private FakeRatingDto ratingDto;
}

