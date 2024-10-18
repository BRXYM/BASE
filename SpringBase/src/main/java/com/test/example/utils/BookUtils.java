package com.test.example.utils;

import com.test.example.domain.Book;

public class BookUtils {
    public Book replaceBookValues(Book book, Book alterBook) {
        book.setBookUserId(alterBook.getBookUserId() != null? alterBook.getBookUserId(): book.getBookUserId());
        book.setBookClassroomId(alterBook.getBookClassroomId() != null? alterBook.getBookClassroomId(): book.getBookClassroomId());
        book.setBookWaiterId(alterBook.getBookWaiterId() != null? alterBook.getBookWaiterId(): book.getBookWaiterId());
        book.setAudit(alterBook.getAudit()!= null? alterBook.getAudit(): book.getAudit());
        book.setBookStartTime(alterBook.getBookStartTime() != null? alterBook.getBookStartTime(): book.getBookStartTime());
        book.setBookEndTime(alterBook.getBookEndTime() != null? alterBook.getBookEndTime(): book.getBookEndTime());
        System.out.println("Book修改后结果");
        System.out.println(book);
        return book;
    }
}
