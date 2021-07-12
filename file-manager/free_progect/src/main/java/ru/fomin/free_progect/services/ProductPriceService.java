package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.entities.ProductPriceEn;

import java.util.Optional;

public interface ProductPriceService {

    OrderItem getOrderItemByProductId(Long productId);

    OrderItem getOrderItemByProductPriceId(Long productPriceId);

    ProductPriceEn getOrderItemEn(Long productPriceId);

    ProductPriceEn findByProductId(Long productId);

    Optional<ProductPriceEn> findByProductAndPrice(Long productId, Long cost);

    ProductPriceEn create(Long cost, ProductEn productEn);

}
