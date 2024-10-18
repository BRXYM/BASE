package com.test.example.controller;


import com.test.example.service.BookService;
import com.test.example.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
