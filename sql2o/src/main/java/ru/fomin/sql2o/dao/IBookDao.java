package ru.fomin.sql2o.dao;

import ru.fomin.sql2o.model.Book;

import java.util.List;

public interface IBookDao {

    List<Book> getBooks();

    void createBook(Book book);

}
