package ru.fomin.free_progect.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "prices")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceEn extends AbstractPersistable<Long> {

    Long cost;

    @OneToMany(mappedBy = "price")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<ProductPriceEn> productPrice;

    public PriceEn(Long cost) {
        this.cost = cost;
    }
}
