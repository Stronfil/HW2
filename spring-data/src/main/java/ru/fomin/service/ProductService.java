package ru.fomin.service;

import ru.fomin.domain.Product;
import ru.fomin.domain.ProductFilter;
import ru.fomin.domain.ProductPage;

import java.util.List;

public interface ProductService {

    ProductPage getProductsByFilter(ProductFilter productFilter, int page);

}
