package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.PriceEn;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.entities.ProductPriceEn;
import ru.fomin.free_progect.mappers.OrderItemMapper;
import ru.fomin.free_progect.repositories.ProductPriceRepository;
import ru.fomin.free_progect.services.PriceService;
import ru.fomin.free_progect.services.ProductPriceService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPriceServiceImpl implements ProductPriceService {

    @Resource
    ProductPriceRepository productPriceRepository;

    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    PriceService priceService;

    @Override
    public OrderItem getOrderItemByProductId(Long productId) {
        ProductPriceEn productPriceEn = findByProductId(productId);
        return orderItemMapper.convertToOrderItem(productPriceEn);
    }

    @Override
    public OrderItem getOrderItemByProductPriceId(Long productPriceId) {
        ProductPriceEn productPriceEn = productPriceRepository.findById(productPriceId)
                .orElseThrow(() -> new RuntimeException("product price was not found"));
        return orderItemMapper.convertToOrderItem(productPriceEn);
    }

    @Override
    public ProductPriceEn getOrderItemEn(Long productPriceId) {
        return productPriceRepository.findById(productPriceId)
                .orElseThrow(() -> new RuntimeException("product price was not found"));
    }

    @Override
    public ProductPriceEn findByProductId(Long productId) {
        return productPriceRepository.findByProduct_Id(productId)
                .orElseThrow(() -> new RuntimeException("product price was not found"));
    }

    @Override
    public Optional<ProductPriceEn> findByProductAndPrice(Long productId, Long cost) {
        return productPriceRepository.findByProduct_IdAndPrice_Cost(productId, cost);
    }

    @Override
    public ProductPriceEn create(Long cost, ProductEn productEn) {
        ProductPriceEn productPriceEn = ProductPriceEn.builder()
                .product(productEn)
                .price(priceService.create(cost))
                .build();
        return productPriceRepository.save(productPriceEn);
    }


}
