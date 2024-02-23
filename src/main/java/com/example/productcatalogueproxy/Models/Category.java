package com.example.productcatalogueproxy.Models;

import com.fasterxml.jackson.databind.ser.Serializers;

import java.util.List;

public class Category extends BaseModel {
    private String desc;
    private String name;
    private List<Product> products;
}
