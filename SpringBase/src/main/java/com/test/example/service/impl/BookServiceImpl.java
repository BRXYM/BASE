package com.test.example.service.impl;

import com.test.example.domain.Book;
import com.test.example.mapper.BookMapper;
import com.test.example.service.BookService;
import com.test.example.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public Result getAllBooks() {
        List<Book> list = bookMapper.selectList(null);
        return new Result(200,"001",list,"获取成功");
    }

    @Override
    public Result addBook(Book book) {
        return null;
    }

    @Override
    public Result deleteBook(Integer bookId) {
        return null;
    }

    @Override
    public Result updateBook(Book book) {
        return null;
    }
}
