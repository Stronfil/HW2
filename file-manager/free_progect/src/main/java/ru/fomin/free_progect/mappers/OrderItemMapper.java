package ru.fomin.free_progect.mappers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.OrderItemEn;
import ru.fomin.free_progect.entities.ProductEn;
import ru.fomin.free_progect.entities.ProductPriceEn;
import ru.fomin.free_progect.services.ProductPriceService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemMapper {

    @Resource
    ProductMapper productMapper;

    @Resource
    ProductPriceService productPriceService;

    public List<OrderItem> convertToOrderItemList(List<OrderItemEn> orderItemList) {
        return orderItemList.stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList());
    }

    public OrderItem convertToOrderItem(OrderItemEn orderItem) {
        return OrderItem.builder()
                .product(productMapper.convertToProduct(orderItem.getProductPrice().getProduct()))
                .productPriceId(orderItem.getProductPrice().getId())
                .quantity(orderItem.getQuantity())
                .build();
    }

    public OrderItem convertToOrderItem(ProductPriceEn productPriceEn) {
        return OrderItem.builder()
                .product(productMapper.convertToProduct(productPriceEn.getProduct()))
                .productPriceId(productPriceEn.getId())
                .build();
    }

    public OrderItemEn convertToOrderItemEn(OrderItem orderItem) {
        ProductEn productEn = productPriceService.getOrderItemEn(orderItem.getProductPriceId()).getProduct();
        return new OrderItemEn(productEn, orderItem.getQuantity());
    }

    public List<OrderItemEn> convertToOrderItemEnList(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .map(this::convertToOrderItemEn)
                .collect(Collectors.toList());
    }

}
