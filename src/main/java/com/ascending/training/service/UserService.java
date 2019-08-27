package com.ascending.training.service;

import com.ascending.training.model.Customer;
import com.ascending.training.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public boolean save(Customer customer){
        return customerDao.save(customer);
    }

    public int update(Customer customer){return customerDao.update(customer);}
    public int delete(long userId){return customerDao.delete(userId);}

    public List<Customer> getCustomer(){return customerDao.getUsers();}

    public List<Customer> getCustomersByName(String customerName){
        return customerDao.getCustomersByName(customerName);
    }

    long toString(Customer b){
        return b.getId();
    }
}
