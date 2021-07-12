package ru.fomin.dao;

import java.util.List;
import java.util.Optional;

/**
 * Universal base repository interface.
 */
public interface IDao<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T saveOrUpdate(T model);

    void delete(ID id);

}
