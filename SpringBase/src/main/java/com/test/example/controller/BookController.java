package com.test.example.controller;


import com.test.example.domain.Book;
import com.test.example.service.BookService;
import com.test.example.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public Result getAllBooks() {
        return bookService.getAllBooks();
    }
    @PostMapping("/addBook")
    public Result addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/deleteBook")
    public Result deleteBook(@RequestBody Book book) {
        return bookService.deleteBook(book);
    }

    @PostMapping("/updateBook")
    public Result updateBook(@RequestBody Book book) {
        System.out.println(book);
        return bookService.updateBook(book);
    }
}
