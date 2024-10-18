package com.test.example.service;

import com.test.example.domain.Book;
import com.test.example.utils.Result;

public interface BookService {
    Result getAllBooks();

    Result addBook(Book book);

    Result deleteBook(Book book);

    Result updateBook(Book book);
}
