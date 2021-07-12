package ru.fomin.free_progect.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fomin.free_progect.entities.ProductEn;

@Repository
public interface ProductRepository extends JpaRepository<ProductEn, Long> {

    @Query("select p from ProductEn p where p.productPrice.price.cost >= ?1 and p.productPrice.price.cost <= ?2")
    Page<ProductEn> findAllByMinAndMaxPrice(Long min, Long max, Pageable pageable);

}
