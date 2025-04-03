package com.driver.repositories;

import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class UserRepository {

    private static final HashMap<Integer, User> users = new HashMap<>();

    public UserRepository(){
    }

    public Optional<User> addUser(User user){
        if(users.get(user.getaadharCardNo())!=null){
            return Optional.empty();
        }
        users.put(user.getaadharCardNo(),user);
        return Optional.of(user);
    }

}
