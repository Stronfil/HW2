package ru.fomin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fomin.entity.ProductEn;

@Repository
public interface ProductRepository extends JpaRepository<ProductEn, Long> {

    Page<ProductEn> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Long min, Long max, Pageable pageable);

}
