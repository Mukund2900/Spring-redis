package com.Demo.springdataredisexample;

import com.Demo.springdataredisexample.entity.Product;
import com.Demo.springdataredisexample.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping("/product")
@EnableCaching
public class SpringDataRedisExampleApplication {
	@Autowired
	private ProductDao dao;

	@PostMapping("/saveNew")
	public Product save(@RequestBody Product product) {
		return dao.save(product);
	}

	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return dao.findAll();
	}

	@GetMapping("find/{id}")
	@Cacheable(key = "#id" , value = "Product")
	public Product findProduct(@PathVariable int id) {
		return dao.findProductById(id);
	}
	@DeleteMapping("delete/{id}")
	public String remove(@PathVariable int id)   {
		return dao.deleteProduct(id);
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisExampleApplication.class, args);
	}

}
