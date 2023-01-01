package com.javid.clothingstore.controllers;

import com.javid.clothingstore.config.SecurityConfig;
import com.javid.clothingstore.model.*;
import com.javid.clothingstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ReviewsServiceImpl reviewsService;
    @Autowired
    private CartLinesService cartLinesService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/account")
    public String myAccount(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userOrderList", user.getOrderList());
        model.addAttribute("userAddressList", user.getAddress());
        model.addAttribute("userDashboard", user.getAccount().getDashboard());
        model.addAttribute("classActiveEdit", true);

        return "my-account";
    }



    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String updateUser(
            @ModelAttribute("firstName") String firstName,
            @ModelAttribute("lastName") String lastName,
            @ModelAttribute("mobile") String mobile,
            @ModelAttribute("email") String email,
            @ModelAttribute("address") String address, Model model, Principal principal) {

    User user = userService.findByUsername(principal.getName());
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setMobile(mobile);
    user.setEmail(email);
    user.setAddress(address);
    userService.updateUser(user);



    model.addAttribute("user", user);
    model.addAttribute("firstName", firstName);
    model.addAttribute("lastName", lastName);
    model.addAttribute("mobile", mobile);
    model.addAttribute("email", email);
    model.addAttribute("userAddress", address);
    model.addAttribute("UserOrderList", user.getOrderList());


        return "my-account";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(@ModelAttribute("user") User user,
                                 @ModelAttribute("newPassword") String newPassword,
                                 @ModelAttribute("confirmPassword") String confirmPassword, Model model) throws Exception {

        User currentUser = userService.findById(user.getId());

        if(currentUser == null) {
            throw new Exception("User not Found");
        }
        if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("") && confirmPassword != null
            && !confirmPassword.isEmpty() && !confirmPassword.equals("")) {
            if(newPassword == confirmPassword) {
                BCryptPasswordEncoder bCryptPasswordEncoder = SecurityConfig.passwordEncoder();
                String dbPassword = currentUser.getPassword();

                if(bCryptPasswordEncoder.matches(user.getPassword(), dbPassword)) {
                    currentUser.setPassword(passwordEncoder.encode(newPassword));
                    currentUser.setRetypePassword(passwordEncoder.encode(confirmPassword));
                }else {
                    model.addAttribute("IncorrectPassword", true);
                    return "my-account";
                }

            }else {
                model.addAttribute("passwordIsNotEqual", true);
            }

            model.addAttribute("user", currentUser);

        }

        userService.save(currentUser);


        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "my-account";

    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUserPost(    @RequestParam(value = "firstName", required = false) String firstName,
                                  @RequestParam(value = "lastName", required = false) String lastName,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "mobile", required = false) String mobile,
                                  @RequestParam(value = "password", required = false) String password,
                                  @RequestParam(value = "retypePassword", required = false) String retypePassword,
                                  @RequestParam(value = "username", required = false) String username, Model model) throws Exception {


        if(userService.findByEmail(email) != null) {
            model.addAttribute("emailExists", true);
            return "login";
        }

        if(userService.findByUsername(username) !=  null) {
            model.addAttribute("usernameExists", true);
            return "login";
        }

        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setUsername(username);

        if(password.equals(retypePassword)) {
            user.setPassword(passwordEncoder.encode(password));
            user.setRetypePassword(passwordEncoder.encode(password));
        }else {
            model.addAttribute("passwordIsNotEqual", true);
        }


        userService.createUser(user);

        return "login";

    }


    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newUserGet() {
        return "login";
    }




    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }


    @RequestMapping("/product-detail/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Product product = productService.findOne(id);
        List<Product> productList = productService.findAll();
        List<Reviews> reviewsList = reviewsService.findByProductId(id);
       model.addAttribute("product", product);
        model.addAttribute("productList", productList);
        model.addAttribute("reviewsList", reviewsList);

        return "product-detail";
    }

    @RequestMapping("/add-product")
    public String ProductGet() {
        return "add-product";
    }

    @RequestMapping("/product-list")
    public String getAllPages(Model model) {
        return getOnePage(model, 1);
    }

    @RequestMapping("/product-list/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage ) {
        Page<Product> page = productService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> productList = page.getContent();

        model.addAttribute("productList", productList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", currentPage);

        return "product-list";
    }





    @RequestMapping(value = "/add-product", method = RequestMethod.POST)
    public String addProductPost(@RequestParam(value = "brand") String brand,
                             @RequestParam(value = "category") String category,
                             @RequestParam(value = "description") String description,
                             @RequestParam(value = "inStockNumber") int inStockNumber,
                             @RequestParam(value = "color") String color,
                             @RequestParam(value = "productName") String productName,
                             @RequestParam(value = "productPrice") int productPrice,
                             @RequestParam(value = "size") String size,
                             @RequestParam(value = "specification") String specification,
                             @RequestParam(value = "file") MultipartFile file,
                             Model model) {

        Product product = new Product();


        product.setBrand(brand);
        product.setCategory(category);
        product.setDescription(description);
        product.setInStockNumber(inStockNumber);
        product.setProductColor(color);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductSize(size);
        product.setSpecification(specification);
        product.setProductImage(file.getOriginalFilename());



        if(product.getProductImage() != null) {
            try{
                File saveFile = new ClassPathResource("ecommerce-html-template/img/").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path,StandardCopyOption.REPLACE_EXISTING);

            }catch (Exception e) {
            e.printStackTrace();
            }
            productService.save(product);

        }


        return "add-product";
    }

    @RequestMapping(value = "/fillCartLines", method = RequestMethod.POST)
    public String cartLinesPost(@ModelAttribute("product") Product product,
                                @RequestParam("productQuantity") int productQuantity,Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        Product currentProduct = productService.findOne(product.getId());
        Cart cart = user.getCart();



        if(productQuantity <= currentProduct.getInStockNumber()
                && product.getProductColor().equals(currentProduct.getProductColor())
                && product.getProductSize().equals(currentProduct.getProductSize())) {

            currentProduct.setInStockNumber(currentProduct.getInStockNumber() - productQuantity);
            int resultPrice = currentProduct.getProductPrice() * productQuantity;

            List<CartLines> cartLinesList = cartLinesService.findByCart(cart);
            CartLines cartLines = new CartLines();

            cartLines.setProduct(currentProduct);
            cartLines.setCartLineProductPrice(currentProduct.getProductPrice());
            cartLines.setCartLineProductQuantity(cartLines.getCartLineProductQuantity() + productQuantity);
            cartLines.setCartLineProductColor(currentProduct.getProductColor());
            cartLines.setCartLineProductSize(currentProduct.getProductSize());
            cartLines.setCartLineProductName(currentProduct.getProductName());
            cartLines.setCart(cart);
            cartLines.setCartLineProductTotal(new BigDecimal(resultPrice));
            cartLinesList.add(cartLines);
            cart.setCartLinesList(cartLinesList);
            cart.setUser(user);
            user.setCart(cart);



            cartLinesService.saveCartLines(cartLines);



            cart =  cartService.getAllPrices(cart);
            cartService.save(cart);




            model.addAttribute("product", currentProduct);



            return "product-detail";
        }

        return "product-detail";
    }




    @RequestMapping("/wishlist")
    public String wishlist() {
        return "wishlist";
    }


    @RequestMapping(value = "/reviews", method = RequestMethod.POST)
    public String reviewsPost(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("review") String review,
                            @ModelAttribute("product") Product product,
                              Model model) {
        Product currentProduct = productService.findOne(product.getId());
        List<Reviews> reviewsList = currentProduct.getReviewsList();
        Reviews reviews = new Reviews();
        reviews.setName(name);
        reviews.setEmail(email);
        reviews.setReview(review);
        reviews.setProduct(currentProduct);
        reviewsList.add(reviews);
        currentProduct.setReviewsList(reviewsList);
        reviewsService.saveReviews(reviews);


        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewsList", reviewsList);
        return "redirect:/product-detail/" + product.getId();

    }

}
