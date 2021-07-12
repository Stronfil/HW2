package ru.fomin.sql2o.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.fomin.sql2o.model.Book;
import ru.fomin.sql2o.services.IBookService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    IBookService bookService;

    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBookList(Model model) {
        List<Book> bookList = bookService.getBooks();
        model.addAttribute("books", bookList);
        return "books";
    }

    @GetMapping("/create")
    public String getFormForCreationBook(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("book", Book.builder().build());
        model.addAttribute("error", error);
        return "create_book";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute Book book) {
        if (!bookService.isUniqueTitle(book)) {
            return "redirect:/book/create?error=title";
        } else if (bookService.createBook(book)) {
            return "redirect:/book";
        } else {
            return "redirect:/book/create?error=price";
        }
    }

}
