package ru.fomin.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fomin.domain.ProductFilter;
import ru.fomin.domain.ProductPage;
import ru.fomin.entity.ProductEn;
import ru.fomin.domain.Product;
import ru.fomin.repository.ProductRepository;
import ru.fomin.service.ProductService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    @Value("${pageSize}")
    int pageSize;

    @Resource
    ProductRepository productRepository;

    @Override
    public ProductPage getProductsByFilter(ProductFilter productFilter, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductEn> productEnPage = productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqual(
                productFilter.getMinPriceLong(),
                productFilter.getMaxPriceLong(),
                pageable
        );
        return convertToProductPage(productEnPage);
    }

    private ProductPage convertToProductPage(Page<ProductEn> productEnPage) {
        return ProductPage.builder()
                .productList(convertToProductList(productEnPage.toList()))
                .pageCount(productEnPage.getTotalPages())
                .build();
    }

    private List<Product> convertToProductList(List<ProductEn> productEnList) {
        return productEnList.stream()
                .map(this::convertToProduct)
                .collect(Collectors.toList());
    }

    private Product convertToProduct(ProductEn productEn) {
        Long price = productEn.getPrice();
        return Product.builder()
                .id(productEn.getId())
                .title(productEn.getTitle())
                .priceRub((int) (price / 100))
                .pricePenny((int) (price % 100))
                .build();
    }

}
