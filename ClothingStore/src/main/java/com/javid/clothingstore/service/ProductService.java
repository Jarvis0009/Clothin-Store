package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<Product> findByCategory(String category);

    List<Product> findAll();

    Product findOne(Long id);

    List<Product> blurrySearch(String title);

    List<Product> findByProductPrice(int price);

    Product save(Product product);

    Page<Product> findPage(int pageNumber);


}
