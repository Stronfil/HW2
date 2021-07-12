package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fomin.free_progect.beans.Cart;
import ru.fomin.free_progect.domains.Order;
import ru.fomin.free_progect.entities.OrderEn;
import ru.fomin.free_progect.entities.OrderItemEn;
import ru.fomin.free_progect.mappers.OrderMapper;
import ru.fomin.free_progect.repositories.OrderRepository;
import ru.fomin.free_progect.services.OrderItemService;
import ru.fomin.free_progect.services.OrderService;
import ru.fomin.free_progect.services.UserService;
import ru.fomin.free_progect.util.SecurityUtil;

import javax.annotation.Resource;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {

    @Resource
    Cart cart;

    @Resource
    OrderItemService orderItemService;

    @Resource
    OrderRepository orderRepository;

    @Resource
    UserService userService;

    @Resource
    OrderMapper orderMapper;

    @Override
    @Transactional
    public Long createOrder() {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        OrderEn orderEn = new OrderEn(
                userService.findCurrentUser(),
                getTotalPriceCart()
        );
        List<OrderItemEn> orderItemEnList = orderItemService.create(cart.getOrderItemList(), orderEn);
        orderEn.setItems(orderItemEnList);
        cart.clearCart();
        return orderRepository.save(orderEn).getId();
    }

    @Override
    @Transactional
    public List<Order> getOrderList() {
        return orderMapper.convertToOrderList(orderRepository.findAllByUser_EmailOrderByCreatedAtDesc(SecurityUtil.getEmail()));
    }

    @Override
    @Transactional
    public Order getOrder(Long orderId) {
        OrderEn orderEn = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("order was not found"));
        verifyAccess(orderEn);
        return orderMapper.convertToOrder(orderEn);
    }

    private Long getTotalPriceCart() {
        return cart.getTotalPriceRub() * 100L + cart.getTotalPricePenny();
    }

    @SneakyThrows
    private void verifyAccess(OrderEn orderEn) {
        if(!SecurityUtil.getEmail().equals(orderEn.getUser().getEmail())){
            throw new AccessDeniedException("access denied");
        }
    }

}
