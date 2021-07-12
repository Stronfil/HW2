package ru.fomin.free_progect.services.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.fomin.free_progect.entities.PriceEn;
import ru.fomin.free_progect.repositories.PriceRepository;
import ru.fomin.free_progect.services.PriceService;

import javax.annotation.Resource;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceServiceImpl implements PriceService {

    @Resource
    PriceRepository priceRepository;

    @Override
    public PriceEn create(Long cost) {
        return priceRepository.save(new PriceEn(cost));
    }

}
