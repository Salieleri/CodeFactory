package com.example.bookservice.repository;

import com.example.bookservice.entity.Book;
import com.example.bookservice.entity.model.RentalServiceModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    List<RentalServiceModel> GetRentalMessageByusername(String username,int rear,int front);

    List<Book> GetRentableListBystatus(int front,int rear);

    List<RentalServiceModel> GetReturnMessageByusername(String username,int rear,int front);

    Optional<Integer> GetRentbookBystatusandbooknum(String booknum);

    void RentBook(String booknum);

    void AddRentIndex(String username,String booknum);

    void ReturnBook(String booknum);

    void SubRentIndex(String username,String booknum);

    int Getmaxbookindex();

    int Getmaxrentalmessageindex(String username);

    int Getmaxreturnmessageindex(String username);
}
