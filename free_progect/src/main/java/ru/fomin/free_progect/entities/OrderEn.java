package ru.fomin.free_progect.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEn extends BaseEn {

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEn user;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<OrderItemEn> items;

    Long totalPrice;

    public OrderEn(UserEn user, List<OrderItemEn> items, Long totalPrice) {
        this.user = user;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public OrderEn(UserEn user, Long totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }

}
