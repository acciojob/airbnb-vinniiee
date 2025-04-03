package com.driver.services;

import com.driver.model.User;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public Integer addUser(User user){
        Optional<User> savedUser = userRepository.addUser(user);
        if(savedUser.isPresent()){
            return savedUser.get().getaadharCardNo();
        }else{
            return -1;
        }
    }

}
