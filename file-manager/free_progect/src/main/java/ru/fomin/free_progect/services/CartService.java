package ru.fomin.free_progect.services;

public interface CartService {

    void addProduct(Long productId);

    void removeProduct(Long orderItemId);

}
