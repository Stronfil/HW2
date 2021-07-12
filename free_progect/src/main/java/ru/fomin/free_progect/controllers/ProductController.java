package ru.fomin.free_progect.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.fomin.free_progect.domains.ProductFilter;
import ru.fomin.free_progect.domains.Product;
import ru.fomin.free_progect.domains.ProductPage;
import ru.fomin.free_progect.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductController {

    ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping
    public String getStartPosition() {
        return "redirect:/product/0";
    }

    @GetMapping("/{page}")
    public String getProductList(Model model,
                                 @PathVariable Integer page,
                                 @ModelAttribute ProductFilter productFilter) {
        ProductPage productPage = productService.getProductsByFilter(productFilter, page);
        List<Product> productList = productPage.getProductList();
        model.addAttribute("products", productList);
        model.addAttribute("filter", productFilter);
        model.addAttribute("page", page);
        model.addAttribute("pageCount", productPage.getPageCount());
        return "products";
    }

}
