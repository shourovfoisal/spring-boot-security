package com.shourov.springbootsecurity.service;

import com.shourov.springbootsecurity.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    List<User> users = new ArrayList<User>();

    public UserService () {

        User shourov = User.builder().username("shourov").email("shourov@gmail.com").password("1234").build();
        User foisal = User.builder().username("foisal").email("foisal@hotmail.com").password("4321").build();

        users.add(shourov);
        users.add(foisal);
    }

    public List<User> getAllUsers() {
        return this.users;
    }

    public User getUser(String username) {
        return this.users.stream().filter(u -> u.getUsername().equals(username)).findAny().orElse(null);
    }

    public User addUser(User user) {
        this.users.add(user);
        return user;
    }


}
