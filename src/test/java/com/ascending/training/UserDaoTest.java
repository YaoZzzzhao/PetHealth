package com.ascending.training;

import com.ascending.training.model.Customer;
import com.ascending.training.jdbc.UserDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class CustomerDaoTest {
    private UserDao userDao;

    String toString(Customer u){
        return u.getFullName();
    }


    @Before
    public void init(){
        userDao = new UserDao();
    }

    @After
    public void cleanup(){
//        userDao.deleteUser()
        userDao = null;
    }

    @Test
    public void getUserTest(){
        List<Customer> customers = userDao.getUsers();
        for (Customer t : customers) {
//            assertEquals("",t.getUsername());
            System.out.println(t.getFullName());
//            System.out.println(t.toString());
        }
    }


    @Test
    public void insertUserTest(){
        Customer customer2 = new Customer();
        Date d = new Date(119,2,3);
        String name = "Curry";
        customer2.setId(1);
        customer2.setFullName(name);
        customer2.setEmail("Cooker@yahoo.com");
        customer2.setPassword("kkyyttmacs");
        customer2.setPetNum(3);
        customer2.setRegisDate(d);
        customer2.setPetType("CAT");


        userDao.insertUser(customer2);

        Assert.assertEquals(customer2.getFullName(),name);


    }

    @Test
    public void updateUserTest(){
        Customer customer3 = new Customer();
        Date d = new Date(119,2,3);
        String name = "Curry";
        String email = "Curry@yahoo.com";
        customer3.setId(1);
        customer3.setFullName(name);
        customer3.setEmail(email);
        customer3.setPetNum(3);
        customer3.setRegisDate(d);
        customer3.setPetType("CAT");

        userDao.updateUser(customer3);

        Assert.assertEquals(customer3.getEmail(),email);
    }

     @Test
    public void deleteUserTest(){
        int d = userDao.deleteUser(1);

        Assert.assertEquals(d,1);
    }
}