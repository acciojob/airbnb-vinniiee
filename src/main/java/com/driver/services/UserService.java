package com.driver.services;

import com.driver.model.User;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;
    public Integer addUser(User user){
        if(user==null)return -1;
        User savedUser = userRepository.addUser(user);
        if(savedUser!=null){
            return savedUser.getaadharCardNo();
        }else{
            return -1;
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
