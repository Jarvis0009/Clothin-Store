package com.javid.clothingstore.controllers;

import com.javid.clothingstore.model.Product;
import com.javid.clothingstore.model.User;
import com.javid.clothingstore.service.ProductService;
import com.javid.clothingstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/searchByCategory")
    public String searchByCategory(@RequestParam("category") String category,
                                   Model model, Principal principal) {
        if(principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        String classActiveCategory = "active" + category;
        classActiveCategory = classActiveCategory.replaceAll("\\s", "");
        model.addAttribute("classActiveCategory", true);

        List<Product> productList = productService.findByCategory(category);

        model.addAttribute("productList", productList);

        return "product-list";
    }

    @RequestMapping(value = "/searchProduct")
    public String searchProduct(@Param("keyword") String keyword,
                                Principal principal, Model model) {
        if(principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }


        List<Product> productList = productService.blurrySearch(keyword);

        if(productList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "product-list";
        }

        model.addAttribute("productList", productList);

        return "product-list";
    }

}
