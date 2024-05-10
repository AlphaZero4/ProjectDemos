package com.example.productcatalogueproxy.Clients;

import com.example.productcatalogueproxy.FakeStoreDtos.FakeProductDto;
import com.example.productcatalogueproxy.FakeStoreDtos.ProductResponseDto;
import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Models.ProductDto;
import com.example.productcatalogueproxy.Repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

@Component
public class FakeStoreAPIClient {

    RestTemplateBuilder restbuilder;
    ProductRepo productRepo;


    public FakeStoreAPIClient(RestTemplateBuilder input) {
        this.restbuilder = input;
    }

    public FakeProductDto getProd(Long id) {
        RestTemplate temp = restbuilder.build();
        FakeProductDto dto = temp.getForEntity("https://fakestoreapi.com/products/{id}", FakeProductDto.class, id).getBody();

        return dto;


    }


    FakeProductDto createProd(FakeProductDto dto) {
        RestTemplate temp = restbuilder.build();
        ResponseEntity<FakeProductDto> response = temp.postForEntity("https://fakestoreapi.com/products", dto, FakeProductDto.class);
        return response.getBody();

    }




    public ProductResponseDto createProduct(ProductDto request){
        //ProductResponseDto responseDto=new ProductResponseDto();
        RestTemplate temp = restbuilder.build();


        ResponseEntity<ProductResponseDto> response = temp.postForEntity("https://fakestoreapi.com/products",request, ProductResponseDto.class);
        System.out.println(response.getBody().getTitle());
        return response.getBody();
        }

    public ProductResponseDto updateProduct(ProductDto request,Long id){
        //Optional<Product> existingProduct = productRepo.findById(id);

        //ProductResponseDto responseDto=new ProductResponseDto();
        //RestTemplate temp = restbuilder.build();

        RestTemplate rest = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<ProductResponseDto> response = rest.exchange("https://fakestoreapi.com/products", HttpMethod.PATCH, new ResponseEntity<>(request, HttpStatus.OK), ProductResponseDto.class);

       // ResponseEntity<ProductResponseDto> response = temp.patchForObject("https://fakestoreapi.com/products", request, ProductResponseDto.class,id);
        return response.getBody();
    }



}