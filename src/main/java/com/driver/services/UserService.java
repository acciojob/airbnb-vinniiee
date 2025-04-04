package com.driver.services;

import com.driver.model.User;
import com.driver.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    UserRepository userRepository = new UserRepository();
    public Integer addUser(User user){
        if(user==null)return -1;
        User savedUser = userRepository.addUser(user);
        if(savedUser!=null){
            return savedUser.getaadharCardNo();
        }else{
            return -1;
        }
    }

}
