package ru.fomin.services;

import ru.fomin.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Optional<Product> getProduct(Long id);

    List<Product> getProducts();

    boolean createProduct(Product product);

    boolean updateProduct(Product product);

    boolean delete(Long id);

}
