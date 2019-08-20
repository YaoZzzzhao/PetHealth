package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private User a;

    @Before
    public void init(){

        a = new User();
        String fullname = "Jaygee";
        String pwd = "122333.0";
        String email = "Kyo@gmail.com";
        Date regisDate = new Date(119,7,9);
        String petType = "CAT";
        int petNum = 1;


//        a.setId(30);
        a.setName(fullname);
        a.setPwd(pwd);
        a.setDate(regisDate);
        a.setType(petType);
        a.setNum(petNum);
        a.setEmail(email);

        userService.save(a);
    }

    @After
    public void cleanUp(){
        userService.delete(a.getId());
        userService = null;
        assertNull(userService);
    }




    @Test
    public void saveTest() {

        String pwd = "122333.0";

        assertEquals(pwd, a.getPwd());
    }

    @Test
    public void updateTest(){
        User kyo = userService.getUserById( 1);
        String newEmail = "Leave@gmail.com";
        kyo.setEmail(newEmail);
        userService.update(kyo);

        assertEquals(kyo.getEmail(), newEmail);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = userService.delete(0);

        assertEquals(1,expectedOfNum);
    }

    long toString(User b){
        return b.getId();
    }

    @Test
    public void getUsersTest(){
        List<User> all = userService.getUsers();
        int expectedOfNumber = 8;


//        for (User i : all){
//            System.out.println(i);
//        }

        Assert.assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getUserByIdTest(){
        long testId = 11;
        User test = userService.getUserById(testId);
        System.out.println(test.getPet());

        assertEquals(testId, test.getId());
    }
}
