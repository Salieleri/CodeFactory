package com.example.bookservice.service;

import com.example.bookservice.entity.User;
import com.example.bookservice.entity.model.LoginServiceModel;
import com.example.bookservice.entity.model.UserMessageModel;

import java.util.ArrayList;

public interface UserService {
    boolean FindUserByusername(String username);

    boolean FindUserByemail(String email);

    boolean FindUserByusernameAndpassword(String username,String password);

    boolean RegisterUser(User user);

    UserMessageModel GetUserByusername(String username);

    int FindPrivilegeByusername(String username);

    void alteruserspassword(String username,String password);
}
