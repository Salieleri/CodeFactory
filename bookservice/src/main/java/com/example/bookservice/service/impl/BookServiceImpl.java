package com.example.bookservice.service.impl;

import com.example.bookservice.entity.Book;
import com.example.bookservice.entity.model.RentalServiceModel;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<RentalServiceModel> GetRentalMessageByusername(String username,int page) {
        int front = page * 10 ;
        int rear = (page - 1) * 10;
        return bookRepository.GetRentalMessageByusername(username,rear,front);
    }

    @Override
    public List<RentalServiceModel> GetReturnMessageByusername(String username, int page) {
        int front = page * 10 ;
        int rear = (page - 1) * 10;
        return bookRepository.GetReturnMessageByusername(username,rear,front);
    }

    @Override
    public List<Book> GetRentableListBystatus(int page) {
        int front = page * 10 ;
        int rear = (page - 1) * 10;
        return bookRepository.GetRentableListBystatus(front,rear);
    }

    @Override
    public boolean GetRentbookBystatusandbooknum(String booknum) {
        return bookRepository.GetRentbookBystatusandbooknum(booknum).isPresent();
    }

    @Override
    public void ReturnBook(String username, String booknum) {
        bookRepository.ReturnBook(booknum);
        bookRepository.SubRentIndex(username,booknum);
    }

    @Override
    public void RentBook(String username,String booknum) {
        bookRepository.RentBook(booknum);
        bookRepository.AddRentIndex(username,booknum);
    }

    @Override
    public int Getmaxbookindex() {
        return bookRepository.Getmaxbookindex();
    }

    @Override
    public int Getmaxrentalmessageindex(String username) {
        return bookRepository.Getmaxrentalmessageindex(username);
    }

    @Override
    public int Getmaxreturnmessageindex(String username) {
        return bookRepository.Getmaxreturnmessageindex(username);
    }
}
