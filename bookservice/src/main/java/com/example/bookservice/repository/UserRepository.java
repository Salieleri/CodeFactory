package com.example.bookservice.repository;

import com.example.bookservice.entity.User;
import com.example.bookservice.entity.model.LoginServiceModel;
import com.example.bookservice.entity.model.UserMessageModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<LoginServiceModel> FindUserByusername(String username);

    Optional<LoginServiceModel> FindUserByemail(String email);

    Optional<LoginServiceModel> FindUserByusernameAndpassword(String username,String password);

    UserMessageModel GetUserByusername(String username);

    int RegisterUser(User user);

    int FindPrivilegeByusername(String username);

    void alteruserspassword(String username,String password);
}
