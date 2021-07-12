package ru.fomin.sql2o.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fomin.sql2o.dao.IBookDao;
import ru.fomin.sql2o.model.Book;
import ru.fomin.sql2o.services.IBookService;

import java.util.List;

@Service
public class BookService implements IBookService {

    IBookDao bookDao;

    @Autowired
    public void setBookDao(IBookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public boolean createBook(Book book) {
        if (book.getYearOfRelease() <= 0) {
            return false;
        }
        bookDao.createBook(book);
        return true;
    }

    @Override
    public boolean isUniqueTitle(Book book) {
        String title = book.getTitle();
        return getBooks().stream()
                .allMatch(currentBook -> !currentBook.getTitle().equals(title));
    }

}
