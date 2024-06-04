package com.example.productcatalogueproxy.Repos;

import com.example.productcatalogueproxy.Models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {

    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findProductByPriceBetween(Double low, Double high);

    List<Product> findAllByOrderByIdDesc();

        List<Product> findByTitleLike(String query, Pageable pageable);
        List<Product> findByTitleLike(String query);

        //List<Product>



}
