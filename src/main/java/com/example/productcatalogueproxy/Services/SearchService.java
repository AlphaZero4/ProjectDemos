package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.Repos.ProductRepo;
import com.example.productcatalogueproxy.Models.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService  {

    private ProductRepo productRepo;

    SearchService(ProductRepo repo){
        this.productRepo = repo;
    }

    public List<Product> seachProductsPage(String query,int  pageNum,int pageSize){
    //public List<Product> seachProducts(String query){
//return productRepo.findByTitle(query, PageRequest.of(pageNum,pageSize));
        Sort sort = Sort.by("price").and(Sort.by("id").descending());
        return productRepo.findByTitleLike(query,PageRequest.of(pageNum,pageSize,sort));

    }

    public List<Product> seachAllProducts(String query){
        //public List<Product> seachProducts(String query){
//return productRepo.findByTitle(query, PageRequest.of(pageNum,pageSize));
        Sort sort = Sort.by("price").and(Sort.by("id").descending());
        return productRepo.findByTitleLike(query);

    }

}
