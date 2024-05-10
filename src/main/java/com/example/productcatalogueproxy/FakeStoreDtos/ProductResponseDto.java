package com.example.productcatalogueproxy.FakeStoreDtos;

import com.example.productcatalogueproxy.Models.RatingDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private RatingDto ratingDto;
}
