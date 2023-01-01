package com.javid.clothingstore.controllers;

import com.javid.clothingstore.model.*;
import com.javid.clothingstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private BillingAddressService  billingAddressService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartLinesService cartLinesService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    @RequestMapping("/cart")
    public String cart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("cartLinesList", user.getCart().getCartLinesList());
        model.addAttribute("cartGrandTotal", user.getCart());
        return "cart";
    }


    @RequestMapping("/checkout")
    public String checkout(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("totalCartForCheckout", user.getCart());

        return "checkout";
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String sendShippingAddress(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("mobile") String mobile,
                                      @RequestParam("address") String address,
                                      @RequestParam("country") String country,
                                      @RequestParam("city") String city,
                                      @RequestParam("state") String state,
                                      @RequestParam("zipCode") String zipCode,
                                      @RequestParam("paymentType") String paymentType, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        Cart cart = user.getCart();
        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setBillingAddressFirstName(firstName);
        billingAddress.setBillingAddressLastName(lastName);
        billingAddress.setMobileNumber(mobile);
        billingAddress.setBillingAddressCountry(country);
        billingAddress.setBillingAddressCity(city);
        billingAddress.setBillingAddressState(state);
        billingAddress.setBillingAddressStreet(address);
        billingAddress.setBillingAddressZipcode(zipCode);
        Order order = new Order();
        order.setBillingAddress(billingAddress);
        order.setOrderTotal(cart.getGrandTotal());
        order.setOrderStatus("active");
        order.setUser(user);
        billingAddress.setOrder(order);
        Payment payment = new Payment();
        payment.setPaymentType(paymentType);
        payment.setOrder(order);
        order.setPayment(payment);
        List<CartLines> cartLinesList = user.getCart().getCartLinesList();
        for (CartLines cartLines: cartLinesList ) {
            cartLines.setOrder(order);
        }

        orderService.saveOrder(order);
        paymentService.savePayment(payment);
        billingAddressService.saveBillingAddress(billingAddress);
        cartService.clearCart(cart);


        return "login";

    }



    @RequestMapping("/removeProduct")
    public String removeProduct(@ModelAttribute("cartLines") CartLines cartLines, Model model, Principal principal) {
    User user = userService.findByUsername(principal.getName());
    Cart cart = user.getCart();
    cartLines = cartLinesService.findById(cartLines.getId());
    Product product = productService.findOne(cartLines.getProduct().getId());
    product.setInStockNumber(product.getInStockNumber() + cartLines.getCartLineProductQuantity());
    BigDecimal cartTotal = cart.getGrandTotal().subtract(cartLines.getCartLineProductTotal());
    cart.setGrandTotal(cartTotal);
        List<CartLines> cartLinesList = cartLinesService.findByCart(cart);
        cartLinesList.remove(cartLines);
        cart.setCartLinesList(cartLinesList);

    cartLinesService.deleteById(cartLines.getId());
    cartService.save(cart);
    productService.save(product);
    return "redirect:/cart";
    }
    }
