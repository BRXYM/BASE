package com.test.example.utils;

import com.test.example.domain.Book;

import java.util.Objects;

public class BookUtils {
    public Book replaceBookValues(Book book, Book alterBook) {
        book.setBookUserId(alterBook.getBookUserId() != null? alterBook.getBookUserId(): book.getBookUserId());
        book.setBookClassroomId(alterBook.getBookClassroomId() != null? alterBook.getBookClassroomId(): book.getBookClassroomId());
        book.setBookWaiterId(alterBook.getBookWaiterId() != null? alterBook.getBookWaiterId(): book.getBookWaiterId());
        book.setAudit(!Objects.equals(alterBook.getAudit(), "") ? alterBook.getAudit(): book.getAudit());
        book.setBookStartTime(!Objects.equals(alterBook.getBookStartTime(), "") ? alterBook.getBookStartTime(): book.getBookStartTime());
        book.setBookEndTime(!Objects.equals(alterBook.getBookEndTime(), "") ? alterBook.getBookEndTime(): book.getBookEndTime());
        System.out.println("New"+book);
        return book;
    }
}
