package ru.fomin.free_progect.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fomin.free_progect.domains.Product;
import ru.fomin.free_progect.services.ProductService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/moderator")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModeratorController {

    @Resource
    ProductService productService;

    @GetMapping
    public String getModeratorPage(@RequestParam(name = "id", required = false) Long productId, Model model) {
        Product product = productId == null ? null : productService.getProduct(productId);
        model.addAttribute("product", product);
        return "moderator";
    }

    @PostMapping("/update")
    public String updateProduct( @ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/moderator";
    }

}
