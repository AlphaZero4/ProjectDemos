package com.example.productcatalogueproxy.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public abstract class BaseModel {
    private Long id;
    private Date created;
    private Date updated;
    private Status stat;

}
