package com.example.productcatalogueproxy.Models;

import com.fasterxml.jackson.databind.ser.Serializers;

public class Product extends BaseModel {
    private Category cat;
    Double price;
    String desc;
    String name;
}
