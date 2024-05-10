package com.example.productcatalogueproxy;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class DemoTest {
    
    @Test
    public void test_random(){
    Random num = new Random();
    int a = num.nextInt(10);
    assert(a<9 );

    }
    
}
