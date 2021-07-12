package ru.fomin.free_progect.domains;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    Long id;
    List<OrderItem> orderItemList;
    int totalPriceRub;
    int totalPricePenny;
    int totalQuantity;
    String createAt;

}
