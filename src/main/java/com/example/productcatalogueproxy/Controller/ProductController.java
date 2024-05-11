package com.example.productcatalogueproxy.Controller;

import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Models.ProductDto;
import com.example.productcatalogueproxy.Services.iProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController

public class ProductController {
    iProductService s;

    public ProductController(iProductService ps) {
        this.s = ps;
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getprods() {

        //return "List of all prods";
        List<Product> products = s.getProducts();
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<List<Product>>(products, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        //return s.getProduct(id);
        try {
            if (id < 1) {
                throw new IllegalArgumentException("product Id is incorrect");
            }

            Product product = s.getProduct(id);
            //HttpHeaders headers;
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            //throw e;

        }
        //  return "fetch prod with id" + id;

    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody ProductDto request) {
        try {
            Product p =s.createProduct(request);
        System.out.println("Success create");
        return p;}
        catch(Exception e){
            System.out.println("failed in create");
            System.out.println(e.getMessage());
        }
        return null;
    }


    @PatchMapping("/{id}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto request,@PathVariable Long id) {
        try {
            Product response = s.updateProduct(request,id);
            System.out.println("success update");
            return  new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("error while update");
            System.out.println(e.getMessage());
        }
        return  new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    /*package org.example.productcatalogserviceproxy.Controllers;

import org.example.productcatalogserviceproxy.Dtos.ProductDto;
import org.example.productcatalogserviceproxy.Models.Category;
import org.example.productcatalogserviceproxy.Models.Product;
import org.example.productcatalogserviceproxy.Services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProducts() {
        return productService.getProducts();
    }


    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) {
        try {
            if(productId < 1) {
                throw new IllegalArgumentException("product Id is incorrect");
            }
            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add("called by","pagal");
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        } catch(Exception exception) {
            //return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            throw exception;
        }
    }

    @PostMapping("")
    public Product createProduct(@RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
       return productService.createProduct(product);
    }

    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody ProductDto productDto) {
        Product product = getProduct(productDto);
        return productService.updateProduct(id,product);
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }

    //@ExceptionHandler({IllegalArgumentException.class,NullPointerException.class})
    //private ResponseEntity<String> handleException() {
    //    return new ResponseEntity<String>("kuch toh phata hai",HttpStatus.INTERNAL_SERVER_ERROR);
   //}
}*/


