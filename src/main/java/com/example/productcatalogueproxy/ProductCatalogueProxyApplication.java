package com.example.productcatalogueproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({"com.example.productcatalogueproxy.contents"})
public class ProductCatalogueProxyApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductCatalogueProxyApplication.class, args);
        System.out.println("test");
    }

}
