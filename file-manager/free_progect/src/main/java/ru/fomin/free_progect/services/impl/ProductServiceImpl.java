package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fomin.free_progect.entities.ProductPriceEn;
import ru.fomin.free_progect.mappers.ProductMapper;
import ru.fomin.free_progect.domains.Product;
import ru.fomin.free_progect.domains.ProductFilter;
import ru.fomin.free_progect.domains.ProductPage;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.mappers.PageMapper;
import ru.fomin.free_progect.repositories.ProductRepository;
import ru.fomin.free_progect.services.ProductPriceService;
import ru.fomin.free_progect.services.ProductService;

import javax.annotation.Resource;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {

    @Value("${pageSize}")
    int pageSize;

    @Resource
    ProductRepository productRepository;

    @Resource
    ProductPriceService productPriceService;

    @Resource
    ProductMapper productMapper;

    @Resource
    PageMapper pageMapper;

    @Override
    public ProductPage getProductsByFilter(ProductFilter productFilter, int page) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ProductEn> productEnPage = productRepository.findAllByMinAndMaxPrice(
                productFilter.getMinPriceLong(),
                productFilter.getMaxPriceLong(),
                pageable
        );
        return pageMapper.convertToProductPage(productEnPage);
    }

    @Override
    public Product getProduct(Long productId) {
        ProductEn productEn = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("product was not found"));
        return productMapper.convertToProduct(productEn);
    }

    @Override
    public void updateProduct(Product product) {
        ProductEn productEn = productRepository.getById(product.getId());
        Long newPrice = getPriceOnlyPenny(product);
        if (!newPrice.equals(productEn.getProductPrice().getPrice().getCost())) {
            productEn.setProductPrice( productPriceService.create(newPrice, productEn));
        }
        productEn.setTitle(product.getTitle());
        productEn.setDescription(product.getDescription());
        productRepository.save(productEn);
    }

    private Long getPriceOnlyPenny(Product product) {
        return product.getPricePenny() + product.getPriceRub() * 100L;
    }

}
