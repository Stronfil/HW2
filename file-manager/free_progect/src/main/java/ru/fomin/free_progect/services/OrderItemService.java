package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.entities.OrderEn;
import ru.fomin.free_progect.entities.OrderItemEn;

import java.util.List;

public interface OrderItemService {

    List<OrderItemEn> create(List<OrderItem> orderItemList, OrderEn orderEn);

}
