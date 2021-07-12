package ru.fomin.free_progect.domains;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItem {

    @EqualsAndHashCode.Exclude
    Product product;

    Long productPriceId;

    @EqualsAndHashCode.Exclude
    int quantity;

    public void incrementQuantity() {
        quantity++;
    }

    public void decrementQuantity() {
        quantity--;
    }

}
