package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.Models.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface iProductService {
  //  @GetMapping("/products")
    String getprods();

    //@GetMapping("/products/{id}")
    String getprod( Long id);

    String createProduct( ProductDto dto);

    String updateProduct( ProductDto dto);
}
