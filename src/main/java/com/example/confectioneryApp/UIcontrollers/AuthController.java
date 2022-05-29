package com.example.confectioneryApp.UIcontrollers;

import com.example.confectioneryApp.cart.Cart;
import com.example.confectioneryApp.cart.CartService;
import com.example.confectioneryApp.role.Role;
import com.example.confectioneryApp.role.RoleService;
import com.example.confectioneryApp.user.AppUserDetailsService;
import com.example.confectioneryApp.user.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class AuthController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserDetailsService appUserDetailsService;
    private final CartService cartService;
    private final RoleService roleService;

    @Operation(summary = "Get login page")
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @Operation(summary = "Get register page")
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping("/register")
    @Transactional
    public String postRegisterUser(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        Cart cart = new Cart();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleService.findById(2L).get());
        user.setRoleList(roleList);
        cart.setUser(user);
        user.setCart(cart);
        cart.setCartItemList(new ArrayList<>());
        appUserDetailsService.save(user);
        cartService.save(cart);
        return "redirect:/login";
    }
}
