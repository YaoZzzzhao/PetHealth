package com.ascending.training.repository;

import com.ascending.training.model.User;

import java.util.List;

public interface UserDao {
    boolean save(User user);
    boolean update(User user);
    boolean delete(int userId, String userName);

    List<User> getUser();
    User getUserById(int userId);



}
