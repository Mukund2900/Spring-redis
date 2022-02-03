package com.Demo.springdataredisexample.repository;

import com.Demo.springdataredisexample.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "Product";
    @Autowired
    private RedisTemplate template;

    public Product save(Product product){
        template.opsForHash().put(HASH_KEY , product.getId() , product);
        return product;
    }

    public List<Product> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int Id){
        System.out.println("called from database");
        return (Product) template.opsForHash().get(HASH_KEY , Id);
    }

    public String deleteProduct(int Id){
        template.opsForHash().delete(HASH_KEY , Id);
        return "Product Removed";
    }
}
