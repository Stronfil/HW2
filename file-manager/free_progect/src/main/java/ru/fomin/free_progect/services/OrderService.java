package ru.fomin.free_progect.services;

import ru.fomin.free_progect.domains.Order;

import java.util.List;

public interface OrderService {

    Long createOrder();

    List<Order> getOrderList();

    Order getOrder(Long orderId);

}
