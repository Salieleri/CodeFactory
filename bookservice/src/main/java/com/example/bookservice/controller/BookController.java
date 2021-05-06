package com.example.bookservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bookservice.entity.Book;
import com.example.bookservice.entity.model.RentalServiceModel;
import com.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @ResponseBody
    @PostMapping("/history")
    public List<RentalServiceModel> phistory(@RequestBody JSONObject username){
        String gname = (String)username.get("username");
        int page = (int)username.get("page");
        return bookService.GetRentalMessageByusername(gname,page);
    }
    @ResponseBody
    @PostMapping("/getmaxbook")
    public int getmaxrental(@RequestBody JSONObject page){
        int count = (int) page.get("max");
        int max_page = bookService.Getmaxbookindex();
        if(max_page%count==0){
            return max_page/count;
        }
        else return max_page/count+1;
    }
    @ResponseBody
    @PostMapping("/getmaxrental")
    public int getmaxreturn(@RequestBody JSONObject page){
        int count = (int) page.get("max");
        String username = (String)page.get("username");
        int max_page = bookService.Getmaxreturnmessageindex(username);
        if(max_page%count==0){
            return max_page/count;
        }
        else return max_page/count+1;
    }
    @ResponseBody
    @PostMapping("/getname")
    public String getname(HttpServletRequest request){
        String name = (String) request.getSession().getAttribute("username");
        return name;
    }
    @ResponseBody
    @PostMapping("/rental")
    public String prental(@RequestBody JSONObject book){
        String username = (String)book.get("username");
        String booknum = (String)book.get("booknum");
        boolean rented = bookService.GetRentbookBystatusandbooknum(booknum);
        if(!rented){
            return "false";
        }
        else{
            bookService.RentBook(username,booknum);
            return "true";
        }
    }
    @ResponseBody
    @PostMapping("/rentlist")
    public List<Book> rentlist(@RequestBody JSONObject data){
        int page = (int)data.get("page");
        return bookService.GetRentableListBystatus(page);
    }
    @ResponseBody
    @PostMapping("/returnlist")
    public List<RentalServiceModel> returnlist(@RequestBody JSONObject data){
        int page = (int)data.get("page");
        String username = (String)data.get("username");
        return bookService.GetReturnMessageByusername(username,page);
    }
    @ResponseBody
    @PostMapping("/return")
    public String preturn(@RequestBody JSONObject returninfo){
        String username = (String)returninfo.get("username");
        String booknum = (String)returninfo.get("booknum");
        bookService.ReturnBook(username,booknum);
        return "true";
    }
    @GetMapping("/rental")
    public String grental(){
        return "rental";
    }
    @GetMapping("/return")
    public String greturn(){
        return "return";
    }
    @GetMapping("/history")
    public String ghistory(){
        return "history";
    }
    @GetMapping("/message")
    public String message(){
        return "message";
    }
    @GetMapping("/alter")
    public String galter(){
        return "alter";
    }
}
