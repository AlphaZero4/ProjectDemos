package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Models.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;

public interface iProductService {
  //  @GetMapping("/products")
    List<Product> getProducts();

    //@GetMapping("/products/{id}")
    Product getProduct( Long id);

    Product createProduct( ProductDto dto);

    Product updateProduct(ProductDto dto,Long id) throws IOException;
}
