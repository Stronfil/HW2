package ru.fomin.repository;

import ru.fomin.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
}
