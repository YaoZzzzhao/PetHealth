package com.ascending.training.repository;

import com.ascending.training.model.Customer;

import java.util.List;

public interface CustomerDao {
    boolean save(Customer customer);
    int update(Customer customer);
    int delete(long userId);

    List<Customer> getUsers();
    List<Customer> getUsersByName(String name);



}
