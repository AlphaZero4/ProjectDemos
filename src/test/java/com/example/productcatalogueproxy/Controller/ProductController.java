package com.example.productcatalogueproxy.Controller;

import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Services.iProductService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ProductController {
    @Autowired
    iProductService s;

    @GetMapping("")
    public ResponseEntity<List<Product>> getprods() {

        //return "List of all prods";
        List<Product> products = s.getProducts();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(products, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        //return s.getProduct(id);
        try{
            if(id<1){throw new IllegalArgumentException("product Id is incorrect");}

            Product product = s.getProduct(id);
            //HttpHeaders headers;
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(product, headers, HttpStatus.OK);}
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //throw e;

        }

        //  return "fetch prod with id" + id;

    }

}
