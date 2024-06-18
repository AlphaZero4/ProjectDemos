package com.example.productcatalogueproxy.Services;

import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Models.ProductDto;
import com.example.productcatalogueproxy.Models.UserDto;
import com.example.productcatalogueproxy.Repos.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
@Service
public class StorageProductService implements iProductService{
    private ProductRepo productRepo;

    private RestTemplate restTemplate;

    public StorageProductService(RestTemplate restTemplate,ProductRepo productRepo) {
        this.restTemplate=restTemplate;
        this.productRepo = productRepo;
    }

    public Product getProductDetails(Long userId, Long productId) {
        Optional<Product> optional = productRepo.findById(productId);
        Product product = optional.get();

//       RestTemplate restTemplate = new RestTemplate();
        UserDto  userDto = restTemplate.getForEntity("http://userservice/users/{id}", UserDto.class,userId).getBody();
        System.out.println(userDto.getEmail());
        return product;
    }
    @Override
    public List<Product> getProducts() {
        return null;
    }


    @Override
    public Product getProduct(Long id) {
        Optional<Product> product = productRepo.findById(id);
        return product.get();
    }

@Override
    public Product createProduct(ProductDto productdto) {
       return null;
    }


    public Product updateProduct(ProductDto productDto,Long id) {
        return null;
    }


}
