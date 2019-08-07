package com.ascending.training;

import com.ascending.training.jdbc.UserDao;
import com.ascending.training.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    private UserDao usersDao;

    @Before
    public void init(){ usersDao = new UserDao();}

    @Test
    public void getUsersTest(){
        UserDao usersDao = new UserDao();
        List<User> users = usersDao.getUsers();
        int expectedNumofUsers = 1;

        for (User user : users){
            System.out.print(user);
        }

        Assert.assertEquals(expectedNumofUsers, users.size());

    }

}
