package com.example.productcatalogueproxy.Models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date created;
    private Date updated;
    private Status stat;

}
