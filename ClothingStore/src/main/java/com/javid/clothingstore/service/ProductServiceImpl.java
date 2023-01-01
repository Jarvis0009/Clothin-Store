package com.javid.clothingstore.service;

import com.javid.clothingstore.model.Product;
import com.javid.clothingstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findByCategory(String category) {
        List<Product> productList = productRepository.findByCategory(category);
        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList ) {
            if(product.isActive()) {
                activeProductList.add(product);
            }
        }
        return activeProductList;
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        List<Product> activeProductList = new ArrayList<>();

        for (Product product : productList ) {
            if(product.isActive()) {
                activeProductList.add(product);
            }
        }
        return activeProductList;
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> blurrySearch(String productName) {
        List<Product> productList = productRepository.findByProductName(productName);
        List<Product> activeProductList = new ArrayList<>();
        for (Product product : productList ) {
            if(product.isActive()) {
                activeProductList.add(product);
            }
        }

        return activeProductList;
    }

    public Page<Product> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 6);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findByProductPrice(int price) {
        return productRepository.findByProductPrice(price);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }







}
