package com.ascending.training;

import com.ascending.training.jdbc.UsersDao;
import com.ascending.training.model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UsersDaoTest {

    private UsersDao usersDao;

    @Before
    public void init(){ usersDao = new UsersDao();}

    @Test
    public void getUsersTest(){
        UsersDao usersDao = new UsersDao();
        List<Users> users = usersDao.getUsers();
        int expectedNumofUsers = 1;

        for (Users user : users){
            System.out.print(user);
        }

        Assert.assertEquals(expectedNumofUsers, users.size());

    }

}
