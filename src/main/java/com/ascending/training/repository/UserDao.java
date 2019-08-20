package com.ascending.training.repository;

import com.ascending.training.model.User;

import java.util.List;

public interface UserDao {
    boolean save(User user);
    int update(User user);
    int delete(long userId);

    List<User> getUsers();
    User getUserById(long userId);



}
