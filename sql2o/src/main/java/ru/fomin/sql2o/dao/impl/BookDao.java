package ru.fomin.sql2o.dao.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import ru.fomin.sql2o.dao.IBookDao;
import ru.fomin.sql2o.model.Book;

import java.util.List;
import java.util.function.Function;

@Repository
public class BookDao implements IBookDao, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private static final String CREATE_BOOK_QUERY = "INSERT INTO book (title, description, release_year) "
            + "VALUES (:title, :description, :yearOfRelease)";
    private static final String SELECT_BOOK_QUERY = "SELECT * FROM book";

    @Override
    public List<Book> getBooks() {
        return executeQuery(connection -> {
            Query query = connection.createQuery(SELECT_BOOK_QUERY, false);
            query.setColumnMappings(Book.COLUMNS_MAPPING);
            return query.executeAndFetch(Book.class);
        });
    }

    @Override
    public void createBook(Book book) {
        executeQuery(connection -> {
            Query query = connection.createQuery(
                    CREATE_BOOK_QUERY);
            query.setColumnMappings(Book.COLUMNS_MAPPING);
            query.bind(book);
            query.executeUpdate();
            return null;
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private <T> T executeQuery(Function<Connection, T> function) {
        Connection connection = applicationContext.getBean(Connection.class);
        T result = function.apply(connection);
        connection.close();
        return result;
    }
}
