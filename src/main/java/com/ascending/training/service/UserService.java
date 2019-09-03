package com.ascending.training.service;

import com.ascending.training.model.User;
import com.ascending.training.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean save(User user){
        return userDao.save(user);
    }

    public int update(User user){return userDao.update(user);}
    public int delete(long userId){return userDao.delete(userId);}

    public User getUserByCredentials(String email, String password) {
        return userDao.getUserByCredentials(email, password);
    }
    public List<User> getUsers(){return userDao.getUsers();}

    public List<User> getUsersByName(String customerName){
        return userDao.getUsersByName(customerName);
    }
    public User getUserById(long id){
        return userDao.getUserById(id);
    }

    long toString(User b){
        return b.getId();
    }
}
