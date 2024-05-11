package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.Models.BaseModel;
import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Repos.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService  {

    private ProductRepo productRepo;

    SearchService(ProductRepo repo){
        this.productRepo = repo;
    }

    //public List<Product> seachProducts(String query,int  pageNum,int pageSize){
    public List<Product> seachProducts(String query){
//return productRepo.findByTitle(query, PageRequest.of(pageNum,pageSize));
        return productRepo.findByTitleEquals(query);

    }

}
