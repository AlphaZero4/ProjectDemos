package com.example.productcatalogueproxy.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingDto {
    private double rate;
    private Long  count;
}
