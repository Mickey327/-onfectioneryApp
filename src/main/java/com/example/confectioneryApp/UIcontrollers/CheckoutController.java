package com.example.confectioneryApp.UIcontrollers;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.cart.CartItem;
import com.example.confectioneryApp.cart.CartService;
import com.example.confectioneryApp.order.Order;
import com.example.confectioneryApp.order.OrderService;
import com.example.confectioneryApp.user.AppUserDetailsService;
import com.example.confectioneryApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CheckoutController {
    private final CartService cartService;
    private final OrderService orderService;
    private final AppUserDetailsService appUserDetailsService;

    @GetMapping("/checkout")
    public String checkoutPage(Model model,
                             @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        Double price = cart.getCartItemList().stream().mapToDouble(CartItem::countPrice).sum();
        model.addAttribute("cartCount", cart.getCartItemList().size());
        model.addAttribute("total", price);
        model.addAttribute("order", new Order());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String postOrderAfterCheckout(@ModelAttribute("order") Order order,
                                         @AuthenticationPrincipal User user){
        order.setCart(user.getCart());
        orderService.create(order);
        return "redirect:/payment";

    }
    @GetMapping("/payment")
    public String payPage(Model model, @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        Double price = cart.getCartItemList().stream().mapToDouble(CartItem::countPrice).sum();
        model.addAttribute("cartCount", cart.getCartItemList().size());
        model.addAttribute("total", price);
        return "payment";
    }

    @GetMapping("/receipt")
    public String receiptPage(Model model,
                            @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        Optional<Order> orderOptional = orderService.findByCart(cart);
        if (orderOptional.isPresent()) {
            model.addAttribute("order", orderOptional.get());
            Cart cartRefresh = new Cart();
            cartRefresh.setUser(user);
            cartRefresh.setCartItemList(new ArrayList<>());
            cartService.save(cartRefresh);
            user.setCart(cartRefresh);
            appUserDetailsService.save(user);

        }
        model.addAttribute("cartCount", 0);
        user.getCart().setCartItemList(new ArrayList<>());
        cartService.deleteById(cart.getId());
        appUserDetailsService.save(user);
        return "receipt";
    }
}
