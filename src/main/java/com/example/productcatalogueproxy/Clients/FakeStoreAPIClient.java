package com.example.productcatalogueproxy.Clients;

import com.example.productcatalogueproxy.FakeStoreDtos.FakeProductDto;
import com.example.productcatalogueproxy.Models.ProductDto;
import com.example.productcatalogueproxy.FakeStoreDtos.ProductResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;

@Component
public class FakeStoreAPIClient {

    RestTemplateBuilder restbuilder;



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

        return response.getBody();
        }

    public Boolean updateProduct(ProductDto request,Long id) throws IOException {
        //Optional<Product> existingProduct = productRepo.findById(id);
        String url = "https://fakestoreapi.com/products";
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPut patchRequest = new HttpPut(url);
        //ProductResponseDto responseDto=new ProductResponseDto();
        //RestTemplate temp = restbuilder.build();

        RestTemplate rest = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        System.out.println("before");
       // ResponseEntity<ProductResponseDto> response = rest.exchange("https://fakestoreapi.com/products", HttpMethod.PATCH, new HttpEntity<>(request), ProductResponseDto.class);

        // Convert ProductDTO to JSON String
        String jsonString = new ObjectMapper().writeValueAsString(request);

        StringEntity entity = new StringEntity(jsonString);
        patchRequest.setEntity(entity);
        HttpResponse response = client.execute(patchRequest);
        System.out.println("after");
        System.out.println(response.getCode());
       // System.out.println(response.getBody().getTitle());
       // ResponseEntity<ProductResponseDto> response = temp.patchForObject("https://fakestoreapi.com/products", request, ProductResponseDto.class,id);
        return Boolean.TRUE;
    }



}