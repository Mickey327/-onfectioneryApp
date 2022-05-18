package com.example.confectioneryApp.UIcontrollers;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.cart.CartService;
import com.example.confectioneryApp.category.CategoryNotFoundException;
import com.example.confectioneryApp.category.CategoryRepository;
import com.example.confectioneryApp.category.CategoryService;
import com.example.confectioneryApp.product.Product;
import com.example.confectioneryApp.product.ProductService;
import com.example.confectioneryApp.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommonController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;

    @GetMapping(value = {"/","/home"})
    public String indexPage(Model model,
                            @AuthenticationPrincipal User user){
        if (user != null) {
            Cart cart = cartService.findByIdFetch(user.getCart().getId());
            model.addAttribute("cartCount", cart.getCartItemList().size());
        }
        return "index";
    }

    @GetMapping("/shop")
    public String shopPage(Model model,
                           @AuthenticationPrincipal User user){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getAll());
        if (user != null) {
            Cart cart = cartService.findByIdFetch(user.getCart().getId());
            model.addAttribute("cartCount", cart.getCartItemList().size());
        }
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopCategoryPage(@PathVariable("id") Long categoryId,
                                   Model model,
                                   @AuthenticationPrincipal User user){
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("products", productService.getByCategoryId(categoryId));
        if (user != null) {
            Cart cart = cartService.findByIdFetch(user.getCart().getId());
            model.addAttribute("cartCount", cart.getCartItemList().size());
        }
        return "shop";
    }

    @GetMapping("/shop/product/{id}")
    public String shopProductPage(@PathVariable("id") Long productId,
                                  Model model,
                                  @AuthenticationPrincipal User user){
        model.addAttribute("product", productService.getById(productId));
        if (user != null) {
            Cart cart = cartService.findByIdFetch(user.getCart().getId());
            model.addAttribute("cartCount", cart.getCartItemList().size());
        }
        return "viewProduct";
    }

}
