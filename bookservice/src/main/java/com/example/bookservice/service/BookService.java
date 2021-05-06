package com.example.bookservice.service;

import com.example.bookservice.entity.Book;
import com.example.bookservice.entity.model.RentalServiceModel;

import java.util.HashMap;
import java.util.List;

public interface BookService {
    List<RentalServiceModel> GetRentalMessageByusername(String username,int page);

    List<RentalServiceModel> GetReturnMessageByusername(String username,int page);

    List<Book> GetRentableListBystatus(int page);

    boolean GetRentbookBystatusandbooknum(String booknum);

    void RentBook(String username,String booknum);

    void ReturnBook(String username,String booknum);

    int Getmaxbookindex();

    int Getmaxrentalmessageindex(String username);

    int Getmaxreturnmessageindex(String username);
}
