package com.example.productcatalogueproxy.Controller;

import com.example.productcatalogueproxy.FakeStoreDtos.ProductResponseDto;
import com.example.productcatalogueproxy.FakeStoreDtos.SearchRequestDto;
import com.example.productcatalogueproxy.Models.Category;
import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
@Autowired
    private SearchService searchService;


@PostMapping("/all")
    public List<ProductResponseDto> searchAllProucts(@RequestBody   SearchRequestDto req){

    //List<Product> products = searchService.seachProducts(req.getQuery(), req.getPageNum(), req.getPageSize())   ;
    //List<Product> products = searchService.seachProducts(req.getQuery())   ;
    List<Product> products = searchService.seachAllProducts(req.getQuery())      ;
List<ProductResponseDto> dtos = new ArrayList<>();
    for(Product product:products){
        dtos.add(getDtoFromProduct(product));
    }

    return dtos;


}

    @PostMapping("")
    public List<ProductResponseDto> searchProuctsPage(@RequestBody   SearchRequestDto req){

        //List<Product> products = searchService.seachProducts(req.getQuery(), req.getPageNum(), req.getPageSize())   ;
        //List<Product> products = searchService.seachProducts(req.getQuery())   ;
        List<Product> products = searchService.seachProductsPage(req.getQuery(), req.getPageNum(), req.getPageSize())      ;
        List<ProductResponseDto> dtos = new ArrayList<>();
        for(Product product:products){
            dtos.add(getDtoFromProduct(product));
        }

        return dtos;


    }
    private ProductResponseDto getDtoFromProduct(Product productDto) {
        ProductResponseDto product = new ProductResponseDto();

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory().getName());
        //product.setCategory(productDto.getCategory());
        product.setId(productDto.getId());
        return product;
    }



}
