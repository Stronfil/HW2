package ru.fomin.free_progect.beans;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import ru.fomin.free_progect.domains.OrderItem;
import ru.fomin.free_progect.domains.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@SessionScope
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

    List<OrderItem> orderItemList;
    int totalPriceRub;
    int totalPricePenny;
    int totalQuantity;
    Consumer<OrderItem> incrementProductConsumer;


    @PostConstruct
    public void init() {
        orderItemList = new ArrayList<>();
        incrementProductConsumer = orderItem -> {
            orderItem.incrementQuantity();
        };
    }

    public void addProduct(OrderItem addingOrderItem) {
        getOrderItem(addingOrderItem)
                .ifPresentOrElse(incrementProductConsumer, getAddableNewProduct(addingOrderItem));
        refreshPriceAndQuantityAfterAdding(addingOrderItem.getProduct());
    }

    public void removeProduct(OrderItem removingOrderItem) {
        OrderItem currentOrderItem = getOrderItem(removingOrderItem)
                .orElseThrow(() -> new RuntimeException("order item was not found into the cart"));
        currentOrderItem.decrementQuantity();
        if (currentOrderItem.getQuantity() < 1) {
            orderItemList.remove(currentOrderItem);
        }
        refreshPriceAndQuantityAfterRemoving(removingOrderItem.getProduct());
    }

    public void clearCart() {
        orderItemList.clear();
        totalPriceRub = 0;
        totalPricePenny = 0;
        totalQuantity = 0;
    }

    public boolean isEmpty(){
        return orderItemList.isEmpty();
    }

    private Optional<OrderItem> getOrderItem(OrderItem orderItem) {
        return getOrderItem(orderItem.getProductPriceId());
    }

    private Optional<OrderItem> getOrderItem(Long productPriceId) {
        return orderItemList.stream()
                .filter(orderItem -> orderItem.getProductPriceId().equals(productPriceId))
                .findFirst();
    }

    private Runnable getAddableNewProduct(OrderItem orderItem) {
        return () -> {
            orderItem.incrementQuantity();
            orderItemList.add(orderItem);
        };
    }

    private void refreshPriceAndQuantityAfterAdding(Product newProduct) {
        totalQuantity++;
        totalPricePenny += newProduct.getPricePenny();
        totalPriceRub += newProduct.getPriceRub() + totalPricePenny / 100;
        totalPricePenny %= 100;
    }

    private void refreshPriceAndQuantityAfterRemoving(Product removingProduct) {
        totalQuantity--;
        totalPriceRub -= removingProduct.getPriceRub();
        totalPricePenny -= removingProduct.getPricePenny();
        if (totalPricePenny < 0) {
            totalPricePenny += 100;
            totalPriceRub--;
        }
    }

}
