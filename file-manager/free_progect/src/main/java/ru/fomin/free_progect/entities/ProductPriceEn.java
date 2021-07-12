package ru.fomin.free_progect.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "products_prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductPriceEn extends BaseEn {

    @OneToOne
    @JoinColumn(name = "product_id")
    ProductEn product;

    @ManyToOne
    @JoinColumn(name = "price_id")
    PriceEn price;

}
