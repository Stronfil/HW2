package ru.fomin.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fomin.dao.IDao;
import ru.fomin.model.Product;

import java.util.List;
import java.util.Optional;


@Component("productDao")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDaoImpl implements IDao<Product, Long> {

    TransactionalExecutor transactionalExecutor;

    @Autowired
    public void setTransactionalExecutor(TransactionalExecutor transactionalExecutor) {
        this.transactionalExecutor = transactionalExecutor;
    }

    @Override
    public List<Product> findAll() {
        return transactionalExecutor.executeTransaction(
                session -> session.createQuery("FROM Product").list()
        );
    }

    @Override
    public Optional<Product> findById(Long id) {
        return transactionalExecutor.executeTransaction(
                session -> Optional.of(session.get(Product.class, id))
        );
    }

    @Override
    public Product saveOrUpdate(Product model) {
        return (Product) transactionalExecutor.executeTransaction(
                session -> session.merge(model)
        );
    }

    @Override
    public void delete(Long id) {
        transactionalExecutor.executeTransaction(
                session -> {
                    Query query = session.createQuery("DELETE FROM Product WHERE id=:id")
                            .setParameter("id", id);
                    query.executeUpdate();
                    return null;
                }
        );
    }

}
