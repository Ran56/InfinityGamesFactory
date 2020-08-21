package com.infinity.gamesFactory.service;

import com.infinity.gamesFactory.model.User;
import com.infinity.gamesFactory.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {


    @Autowired
    private UserDao userDao;




    public User save(User user){ return userDao.save(user); }
    public void delete(User user){ userDao.delete(user);}
    public User update(User user){ return userDao.update(user);}
    public User getById(Long id){ return userDao.getById(id);}
    public User getUserByEmail(String email){ return userDao.getUserByEmail(email);}
    public List<User> getAllUsers(){return userDao.getAllUsers();}
    public User getUserByName(String name){return userDao.getUserByName(name);}
    public User getUserByCredentials(String email,String password){ return userDao.getUserByCredentials(email,password);}



}
