package ru.fomin.free_progect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fomin.free_progect.entities.OrderItemEn;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEn, Long> {

}
