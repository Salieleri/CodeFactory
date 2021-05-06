package com.example.bookservice.entity;

import java.util.Date;

public class Book {
    private String booknum;
    private String bookname;
    private int status;

    public Book() {
    }

    public Book(String booknum, String bookname, int status) {
        this.booknum = booknum;
        this.bookname = bookname;
        this.status = status;
    }

    public String getBooknum() {
        return booknum;
    }

    public void setBooknum(String booknum) {
        this.booknum = booknum;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
