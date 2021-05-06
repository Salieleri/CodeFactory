package com.example.bookservice.service.impl;

import com.example.bookservice.entity.User;
import com.example.bookservice.entity.model.UserMessageModel;
import com.example.bookservice.repository.UserRepository;
import com.example.bookservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean FindUserByusername(String username) {
        return userRepository.FindUserByusername(username).isPresent();
    }

    @Override
    public boolean FindUserByemail(String email) {
        return userRepository.FindUserByemail(email).isPresent();
    }

    @Override
    public boolean FindUserByusernameAndpassword(String username, String password) {
        return userRepository.FindUserByusernameAndpassword(username,password).isPresent();
    }

    @Override
    public boolean RegisterUser(User user) {
        boolean existed = FindUserByusername(user.getUsername());
        if(!existed){
            int i=userRepository.RegisterUser(user);
            if(i != 0){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public UserMessageModel GetUserByusername(String username) {
        return userRepository.GetUserByusername(username);
    }

    @Override
    public int FindPrivilegeByusername(String username) {
        return userRepository.FindPrivilegeByusername(username);
    }

    @Override
    public void alteruserspassword(String username, String password) {
        userRepository.alteruserspassword(username,password);
    }
}
