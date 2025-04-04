package com.driver.repositories;

import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class UserRepository {

    private static final HashMap<Integer, User> users = new HashMap<>();


    public User addUser(User user){

        if(users.get(user.getaadharCardNo())!=null){
            return null;
        }
        users.put(user.getaadharCardNo(),user);
        return user;
    }

}
