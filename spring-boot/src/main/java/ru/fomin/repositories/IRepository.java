package ru.fomin.repositories;

import java.util.List;
import java.util.Optional;

/**
 * Universal base repository interface.
 */
public interface IRepository<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    /**
     * Creates or updates model.
     *
     * @param model - target model
     * @return - id of the created or updated model
     */
    ID save(T model);

    /**Removes model by key.
     * @return - 'true' if model which has this id exists */
    boolean delete(ID id);

}
