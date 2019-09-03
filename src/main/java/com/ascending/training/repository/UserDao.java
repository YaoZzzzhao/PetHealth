package com.ascending.training.repository;

import com.ascending.training.model.User;

import java.util.List;

public interface UserDao {
    boolean save(User user);
    int update(User user);
    int delete(long userId);
    User getUserByCredentials(String email, String password);

    List<User> getUsers();
    List<User> getUsersByName(String name);
    User getUserById(long id);



}
