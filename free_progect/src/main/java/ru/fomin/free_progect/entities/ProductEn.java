package ru.fomin.free_progect.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEn extends BaseEn {

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @OneToOne
    @JoinColumn(name = "product_price_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    ProductPriceEn productPrice;

    public Long getCurrentPrice(){
        return productPrice.getPrice().getCost();
    }

}
