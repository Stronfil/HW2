package ru.fomin.service.impl;

import ru.fomin.model.Product;
import ru.fomin.repository.ProductRepository;
import ru.fomin.service.ProductService;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String getTableOfProducts() {
        StringBuffer stringBuffer = new StringBuffer("<h1 align='center'>Products<h1>");
        stringBuffer.append("<table align='center' cellspacing='2' border='1' cellpadding='5' width='600'><tr><th>ID</th><th>Title</th><th>Cost</th>");
        for (Product product : productRepository.findAllProducts()) {
            stringBuffer.append("<tr><td>");
            stringBuffer.append(product.getId());
            stringBuffer.append("</td><td>");
            stringBuffer.append(product.getTitle());
            stringBuffer.append("</td><td>");
            Long cost = product.getCost();
            stringBuffer.append(String.format("%.2f rub", ((double) cost) / 100));
            stringBuffer.append("</td></tr>");
        }
        stringBuffer.append("</table>");
        return stringBuffer.toString();
    }
}
