package com.example.productcatalogueproxy.Repos;

import com.example.productcatalogueproxy.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}
