package ru.fomin.servlets;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.fomin.config.AppConfig;
import ru.fomin.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "productList", urlPatterns = "/products")
public class ProductListServlet extends HttpServlet {

    private ProductService productService;
    private AnnotationConfigApplicationContext applicationContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(productService.getTableOfProducts());
    }

    @Override
    public void destroy() {
        applicationContext.close();
    }

    @Override
    public void init() throws ServletException {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        productService = applicationContext.getBean(ProductService.class);
    }
}
