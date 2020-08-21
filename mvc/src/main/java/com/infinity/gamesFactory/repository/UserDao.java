package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.User;

import java.util.List;

public interface UserDao {

    User save(User user);
    void delete(User user);
    User update(User user);
    User getById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User getUserByName(String name);
    User getUserByCredentials(String name,String password);



}
