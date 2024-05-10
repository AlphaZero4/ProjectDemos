package com.example.productcatalogueproxy.Tests.UnitTest;

import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Services.FakeProductService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class test_getid_returnerror {
    @MockBean
    FakeProductService service;

    @Test
    public void test_validate(){
//arrange
        Product p =new Product();
        p.setId(11L);
        p.setDescription("chicha");

        //act
        Boolean flag = check_id(p);


        //assert
assertEquals(true,flag);


    }

    Boolean check_id(Product p){
if(p.getId()>10){

    return false;}
return true;

    }


}
