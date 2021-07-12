package ru.fomin.free_progect.util;

import ru.fomin.free_progect.domains.ProductFilter;

public class UrlMaker {

    public static String getProductUrl(ProductFilter productFilter, Integer page) {
        return String.format(
                "redirect:/product/%d?minPrice=%s&maxPrice=%s",
                page, mapDoubleValueToDot(productFilter.getMinPrice()),
                mapDoubleValueToDot( productFilter.getMaxPrice())
        );
    }

    private static String mapDoubleValueToDot(Double value){
        return String.format("%d.%d", value.intValue(), (int)((value%1)*100));
    }

}
