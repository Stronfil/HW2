package ru.fomin.sql2o.services;

import ru.fomin.sql2o.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> getBooks();

    boolean createBook(Book book);

    boolean isUniqueTitle(Book book);

}
