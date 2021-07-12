package ru.fomin.free_progect.domains;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    Long id;
    String title;
    String description;
    int priceRub;
    int pricePenny;
}
