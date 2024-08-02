package com.example.productcatalogueproxy.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String desc;
    private String name;
    @OneToMany
    private List<Product> products;
}
