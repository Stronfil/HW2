package ru.fomin.free_progect.mappers;

import org.springframework.stereotype.Component;
import ru.fomin.free_progect.domains.Product;
import ru.fomin.free_progect.entities.ProductEn;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public List<Product> convertToProductList(List<ProductEn> productEnList) {
        return productEnList.stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    public Product convertToProduct(ProductEn productEn) {
        Long price = productEn.getCurrentPrice();
        return Product.builder()
                .id(productEn.getId())
                .title(productEn.getTitle())
                .description(productEn.getDescription() != null ? productEn.getDescription() : "")
                .priceRub((int) (price / 100))
                .pricePenny((int) (price % 100))
                .build();
    }

}
