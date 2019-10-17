package com.ascending.training.HibernateTest;

import com.ascending.training.model.User;
import com.ascending.training.jdbc.UserDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UserDaoTest {
    private UserDao userDao;

    String toString(User u){
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
        List<User> users = userDao.getUsers();
        for (User t : users) {
//            assertEquals("",t.getUsername());
            System.out.println(t.getFullName());
//            System.out.println(t.toString());
        }
    }


    @Test
    public void insertUserTest(){
        User user2 = new User();
        Date d = new Date(119,2,3);
        String name = "Curry";
//        user2.setId(12);
        user2.setFullName(name);
        user2.setEmail("Cooker@yahoo.com");
        user2.setPassword("kkyyttmacs");
        user2.setPetNum(3);
        user2.setRegisDate(d);
        user2.setPetType("CAT");


        userDao.insertUser(user2);

        Assert.assertEquals(user2.getFullName(),name);


    }

    @Test
    public void updateUserTest(){
        User user3 = new User();
        Date d = new Date(119,2,3);
        String name = "Curry";
        String email = "Curry@yahoo.com";
//        user3.setId(1);
        user3.setFullName(name);
        user3.setEmail(email);
        user3.setPetNum(3);
        user3.setRegisDate(d);
        user3.setPetType("CAT");

        userDao.updateUser(user3);

        Assert.assertEquals(user3.getEmail(),email);
    }

     @Test
    public void deleteUserTest(){
        int d = userDao.deleteUser(12);

        Assert.assertEquals(d,1);
    }
}