package com.example.confectioneryApp.UIcontrollers;

import com.example.confectioneryApp.category.Category;
import com.example.confectioneryApp.category.CategoryNotFoundException;
import com.example.confectioneryApp.category.CategoryService;
import com.example.confectioneryApp.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final String uploadDir = System.getProperty("user.dir") +
            "/src/main/resources/static/productImages";
    private final CategoryService categoryService;
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping()
    public String adminHomePage(){
        return "adminHome";
    }

    @GetMapping("/categories")
    public String getCategoriesPage(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    @GetMapping("/categories/add")
    public String getCategoriesAddPage(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, Model model){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (categoryOptional.isPresent()){
            model.addAttribute("category", categoryOptional.get());
            return "categoriesAdd";
        }
        throw new CategoryNotFoundException("Вы пытаетесь обновить несуществующую категорию");
    }

    @PostMapping("/categories/add")
    public String postCategoriesAdd(@ModelAttribute("category") Category category){
        categoryService.create(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/products")
    public String getProductsPage(Model model){
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    @GetMapping("/products/add")
    public String getProductsAddPage(Model model){
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories", categoryService.getAll());
        return "productsAdd";
    }

    @PostMapping("/products/add")
    public String postProductsAdd(@ModelAttribute("productDTO") ProductDto productDto,
                                  @RequestParam("productImage")MultipartFile file,
                                  @RequestParam("imgName") String imgName) throws IOException {
        Product product = productMapper.toEntity(productDto);
        if (productDto.getCategoryId() != null) {
            product.setCategory(categoryService.getById(productDto.getCategoryId()));
        }
        String imageUUID;
        if (!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }
        else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.create(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model){
        Optional<Product> productOptional = productService.getById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            ProductDto productDto = productMapper.fromEntity(product);
            productDto.setCategoryId(product.getCategory().getId());
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("productDTO", productDto);
            return "productsAdd";
        }
        throw new ProductNotFoundException("Вы пытаетесь обновить несуществующий товар");
    }

}
