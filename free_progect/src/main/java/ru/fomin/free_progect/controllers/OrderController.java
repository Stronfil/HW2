package ru.fomin.free_progect.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fomin.free_progect.domains.Order;
import ru.fomin.free_progect.services.OrderService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/order")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderController {

    @Resource
    OrderService orderService;

    @GetMapping
    public String getOrders(@RequestParam(required = false) Long id, Model model) {
        Order order = null;
        if (id != null) {
            order = orderService.getOrder(id);
        }
        model.addAttribute("orders", orderService.getOrderList());
        model.addAttribute("order", order);

        return "order";
    }

    @GetMapping("/create")
    public String createOrder() {
        orderService.createOrder();
        return "redirect:/order";
    }

}
