package ru.fomin.free_progect.mappers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.fomin.free_progect.domains.Order;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.OrderEn;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderMapper {

    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    DataMapper dataMapper;

    public List<Order> convertToOrderList(List<OrderEn> orderEnList) {
        return orderEnList.stream()
                .map(this::convertToOrder)
                .collect(Collectors.toList());
    }

    public Order convertToOrder(OrderEn orderEn) {
        List<OrderItem> orderItemList = orderItemMapper.convertToOrderItemList(orderEn.getItems());
        Long totalPriceOnlyPenny = orderEn.getTotalPrice();
        return Order.builder()
                .id(orderEn.getId())
                .orderItemList(orderItemList)
                .totalPriceRub(getTotalPriceRub(totalPriceOnlyPenny))
                .totalPricePenny(getTotalPricePenny(totalPriceOnlyPenny))
                .totalQuantity(getTotalQuantity(orderItemList))
                .createAt(dataMapper.convertToString(orderEn.getCreatedAt()))
                .build();
    }

    private int getTotalQuantity(List<OrderItem> orderItemList) {
        return orderItemList.stream()
                .mapToInt(orderItem -> orderItem.getQuantity())
                .sum();
    }

    private int getTotalPriceRub(Long totalPriceOnlyPenny) {
        return (int) (totalPriceOnlyPenny / 100);
    }

    private int getTotalPricePenny(Long totalPriceOnlyPenny) {
        return (int) (totalPriceOnlyPenny % 100);
    }

}
