package com.example.productcatalogueproxy.Services;


import com.example.productcatalogueproxy.Clients.FakeStoreAPIClient;
import com.example.productcatalogueproxy.FakeStoreDtos.FakeProductDto;
import com.example.productcatalogueproxy.FakeStoreDtos.ProductResponseDto;
import com.example.productcatalogueproxy.Models.Category;
import com.example.productcatalogueproxy.Models.Product;
import com.example.productcatalogueproxy.Models.ProductDto;
import com.example.productcatalogueproxy.Repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Primary
@Service
public class FakeProductService implements iProductService {
    @Autowired
    private RestTemplateBuilder restbuilder;
    @Autowired
    private FakeStoreAPIClient fakeStoreAPIClient;

    //@Autowired
   // add if using redis ---- private RedisTemplate<String,Object> redisTemplate;
    ProductRepo productRepo;

    FakeProductService(ProductRepo repo){
        this.productRepo = repo;
    }

  /*  public FakeProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreAPIClient fakeStoreAPIClient) {
        this.restbuilder = restTemplateBuilder;
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }*/

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = restbuilder.build();
        FakeProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeProductDto[].class).getBody();

        List<Product> products = new ArrayList<>();
        for (FakeProductDto productDto : fakeStoreProductDtos) {
            products.add(getProductFromDto(productDto));
        }
        return products;
    }


    @Override
    public Product getProduct(Long productId) {
        //apply caching

        //check if in cache, if yes then read, else call fakestore  and store result
    FakeProductDto fakeProductDto = null;
   //add if using redis fakeProductDto = (FakeProductDto)redisTemplate.opsForHash().get("PRODUCTS",productId);
    if(fakeProductDto!=null){
        System.out.println("read from CACHE");
        return getProductFromDto(fakeProductDto);
    }
    else{
fakeProductDto = fakeStoreAPIClient.getProd(productId);
       //use if using redis-- redisTemplate.opsForHash().put("PRODUCTS",productId,fakeProductDto);
        System.out.println("read from API");
        return getProductFromDto(fakeProductDto);
    }
    }

    @Override
        public Product getProductDetails(Long userId, Long productId) {
        return null;
    }
    public Product createProduct(ProductDto request){
        //Product product = getProductFromDto(request);

        //FakeProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        ProductResponseDto responseFakeStoreProductDto = fakeStoreAPIClient.createProduct(request);
        System.out.println(responseFakeStoreProductDto.getDescription());
        Product p = getProductFromDto(responseFakeStoreProductDto);
        System.out.println("after save");
        Product saved = productRepo.save(p);
        return saved;
    }

    public Product updateProduct(ProductDto request,Long id) throws IOException {
        //ProductResponseDto responseDto = fakeStoreAPIClient.updateProduct(request,id);
Boolean flag = fakeStoreAPIClient.updateProduct(request,id);
       // Product p = getProductFromDto(responseDto);
        Product p =getProductFromDto(request);
        Product saved = productRepo.save(p);
        System.out.println("after save");
        return saved;

    }

    private Product getProductFromDto(ProductResponseDto productDto) {
        Product product = new Product();

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }
    private Product getProductFromDto(ProductDto productDto) {
        Product product = new Product();

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }
    private Product getProductFromDto(FakeProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }
}


/*


    @Override
    public Product updateProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject("https://fakestoreapi.com/{id}", product, FakeStoreProductDto.class,id);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.PATCH,"https://fakestoreapi.com/{id}",product, FakeStoreProductDto.class,id);
        Product resultantProduct = getProduct(fakeStoreProductDtoResponseEntity.getBody());
        return resultantProduct;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public Product createProduct(Product product) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        //ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.postForEntity("https://fakestoreapi.com/products",product,FakeStoreProductDto.class);
        FakeProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        FakeProductDto responseFakeStoreProductDto = fakeStoreAPIClient.createProduct(fakeStoreProductDto);
        return getProduct(responseFakeStoreProductDto);
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        //FakeStoreProductDto fakeStoreProductDto = restTemplate.patchForObject("https://fakestoreapi.com/{id}", product, FakeStoreProductDto.class,id);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/{id}", product, FakeStoreProductDto.class, id);
        Product resultantProduct = getProduct(fakeStoreProductDtoResponseEntity.getBody());
        return resultantProduct;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProduct(FakeProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setId(productDto.getId());
        return product;
    }

    private FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        return fakeStoreProductDto;
    }
}
*/
    /*
@Service
public class FakeProductService implements iProductService {
    //private RestTemplateBuilder restbuilder;
    public FakeProductService(RestTemplateBuilder restbuilder){
        this.restbuilder = restbuilder;
    }
    @Override

    public String getprods(){

        return "List of all prods";
    }
    @Override

    public String getprod( Long pid){
        RestTemplate restTemplate= restbuilder.build();
        ProductDto response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", ProductDto.class,pid).getBody();
        if(response!=null){    return "pass";}
        return "failed";
        //return "fetch prod with id" + id1;
    }

    @Override
    public String createProduct( ProductDto req){
       // RestTemplate restTemplate= restbuilder.build();
        return null;}
    @Override
    public String updateProduct( ProductDto req){return null;}
}
*/