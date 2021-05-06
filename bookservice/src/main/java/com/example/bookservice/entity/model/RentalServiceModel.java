package com.example.bookservice.entity.model;

import java.util.Date;

public class RentalServiceModel {
    private String booknum;
    private String bookname;
    private Date rentaltime;

    public RentalServiceModel() {
    }

    public RentalServiceModel(String booknum, String bookname, Date rentaltime) {
        this.booknum = booknum;
        this.bookname = bookname;
        this.rentaltime = rentaltime;
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

    public Date getRentaltime() {
        return rentaltime;
    }

    public void setRentaltime(Date rentaltime) {
        this.rentaltime = rentaltime;
    }
}
