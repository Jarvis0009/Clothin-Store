package com.javid.clothingstore;

import com.javid.clothingstore.model.Product;
import com.javid.clothingstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClothingStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingStoreApplication.class, args);
    }


//    @Autowired
//    private ProductRepository productRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        Product product1 = new Product();
//        product1.setProductName("Blue Jeans");
//        Product product2 = new Product();
//        product2.setProductName("Smoking");
//
//        productRepository.save(product1);
//        productRepository.save(product2);
//
//    }
}
