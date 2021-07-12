package ru.fomin.free_progect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fomin.free_progect.entities.PriceEn;

@Repository
public interface PriceRepository extends JpaRepository<PriceEn, Long> {

}
