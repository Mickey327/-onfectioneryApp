package com.example.confectioneryApp.UIcontrollers;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.cart.CartItem;
import com.example.confectioneryApp.cart.CartService;
import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.product.ProductService;
import com.example.confectioneryApp.user.AppUserDetailsService;
import com.example.confectioneryApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final CartService cartService;
    private final AppUserDetailsService appUserDetailsService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id,
                            @AuthenticationPrincipal User user){
        Product product = productService.getById(id);
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        Optional<CartItem> cartItemOptional = cart.getCartItemList().stream()
                .filter(o -> o.getProduct().equals(product)).findFirst();
        if (cartItemOptional.isPresent()){
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity()+1);
        }
        else {
            CartItem cartItem = CartItem
                    .builder()
                    .product(productService.getById(id))
                    .quantity(1)
                    .build();
            cart.getCartItemList().add(cartItem);
        }
        cartService.save(cart);
        return "redirect:/shop/product/{id}";
    }

    @GetMapping("/cart")
    public String cartPage(Model model,
                           @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        Double price = cart.getCartItemList().stream().mapToDouble(CartItem::countPrice).sum();
        model.addAttribute("cartCount", cart.getCartItemList().size());
        model.addAttribute("total", price);
        model.addAttribute("cart", cart.getCartItemList());
        return "cart";
    }

    @GetMapping("/cart/lessQuantity/{index}")
    public String lessCartItemQuantity(@PathVariable("index") int index,
                                       @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        cart.getCartItemList().get(index)
                .setQuantity(cart.getCartItemList().get(index).getQuantity() - 1);
        cartService.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart/addQuantity/{index}")
    public String addCartItemQuantity(@PathVariable("index") int index,
                                       @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        cart.getCartItemList().get(index)
                .setQuantity(cart.getCartItemList().get(index).getQuantity() + 1);
        cartService.save(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String removeCartItem(@PathVariable("index") int index,
                                 @AuthenticationPrincipal User user){
        Cart cart = cartService.findByIdFetch(user.getCart().getId());
        cart.getCartItemList().remove(index);
        cartService.save(cart);
        return "redirect:/cart";
    }
}
