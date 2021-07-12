package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.OrderEn;
import ru.fomin.free_progect.entities.OrderItemEn;
import ru.fomin.free_progect.mappers.OrderItemMapper;
import ru.fomin.free_progect.repositories.OrderItemRepository;
import ru.fomin.free_progect.services.OrderItemService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    OrderItemRepository orderItemRepository;

    @Resource
    OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemEn> create(List<OrderItem> orderItemList, OrderEn orderEn) {
        return orderItemList.stream()
                .map(orderItemMapper::convertToOrderItemEn)
                .peek(orderItemEn -> orderItemEn.setOrder(orderEn))
                .map(orderItemRepository::save)
                .collect(Collectors.toList());
    }

}
