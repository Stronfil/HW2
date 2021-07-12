package ru.fomin.free_progect.domains;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductFilter {

    final Double maximumPriceLimit = 99999.99;

    Double minPrice = 0.0;
    Double maxPrice = maximumPriceLimit;

    public void setMinPrice(Double minPrice) {
        if (minPrice == null || minPrice < 0) {
            minPrice = 0.0;
        }
        this.minPrice = minPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        if (maxPrice == null || maxPrice > maximumPriceLimit) {
            maxPrice = maximumPriceLimit;
        }
        this.maxPrice = maxPrice;
    }

    public Long getMinPriceLong() {
       return getPriceLong(minPrice);
    }

    public Long getMaxPriceLong() {
        return getPriceLong(maxPrice);
    }

    private Long getPriceLong(Double convertingPrince){
        Double price = convertingPrince*100.0;
        return price.longValue();
    }

}
