package ru.fomin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fomin.model.Product;
import ru.fomin.services.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private IProductService productService;

    @Autowired
    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping
    public String getProducts(Model model) {
        List<Product> productList = productService.getProducts();
        model.addAttribute("products", productList);
        return "product";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        model.addAttribute("header", "Create");
        model.addAttribute("product", Product.builder().build());
        return "manipulation";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable(name = "id") Long id){
        productService.delete(id);
        return "redirect:/product";
    }

    @GetMapping("/change/{id}")
    public String changeProduct(Model model, @PathVariable(name = "id") Long id) {
        Product product = productService.getProduct(id).get();
        model.addAttribute("product", product);
        model.addAttribute("header", "Change");
        return "manipulation";
    }

    @PostMapping("/save")
    public String saveProduct(Model model, Product product) {
        if (!(productService.updateProduct(product) || productService.createProduct(product))) {
            model.addAttribute("header", "Fix");
            return "manipulation";
        }
        return "redirect:/product";
    }

}
