package com.test.example.service.impl;

import com.test.example.domain.Book;
import com.test.example.mapper.BookMapper;
import com.test.example.service.BookService;
import com.test.example.utils.BookUtils;
import com.test.example.utils.Result;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;


    @Override
    public Result getAllBooks() {
        List<Book> list = bookMapper.selectList(null);
        if (list != null && !list.isEmpty()) {
            return new Result(200, "001", list, "获取成功");
        } else {
            return new Result(200, "002", list, "列表为空");
        }

    }

    @Override
    public Result addBook(Book book) {
        Integer i = bookMapper.insert(book);
        if (i > 0) {
            return new Result(200, "001", i, "添加成功");
        } else {
            return new Result(200, "002", i, "添加失败");
        }
    }

    @Override
    public Result deleteBook(Book book) {
        if(bookMapper.selectById(book.getBookId()) == null) {
            return new Result(200,"003",book,"对象不存在");
        }
        Integer i = bookMapper.deleteById(book.getBookId());
        if (i > 0) {
            return new Result(200,"001",i,"删除成功");
        } else {
            return new Result(200,"002",i,"删除失败");
        }
    }

    @Override
    public Result updateBook(Book book) {
        Book updateBook = bookMapper.selectById(book.getBookId());
        if (updateBook == null) {
            return new Result(200,"003",book,"对象不存在");
        }
        BookUtils bookUtils = new BookUtils();
        int i = bookMapper.updateById(bookUtils.replaceBookValues(updateBook, book));
        if (i > 0) {
            return new Result(200,"001",updateBook,"修改成功");
        }else {
            return new Result(200,"002",updateBook,"修改失败");
        }
    }
}
