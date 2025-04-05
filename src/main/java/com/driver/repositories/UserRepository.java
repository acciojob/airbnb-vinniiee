package com.driver.repositories;

import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {

    private HashMap<Integer, User> users = new HashMap<>();


    public User addUser(User user){

        if(users.get(user.getaadharCardNo())!=null){
            return null;
        }
        users.put(user.getaadharCardNo(),user);
        return user;
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}
