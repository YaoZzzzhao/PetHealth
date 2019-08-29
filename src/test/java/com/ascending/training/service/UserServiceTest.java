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
        String fullname = "Slim";
        String pwd = "122333.0";
        String email = "Kyo@gmail.com";
        Date regisDate = new Date(119,7,9);
        String petType = "CAT";
        int petNum = 1;


//        a.setId(30);
        a.setFullName(fullname);
        a.setPassword(pwd);
        a.setRegisDate(regisDate);
        a.setPetType(petType);
        a.setPetNum(petNum);
        a.setEmail(email);

        userService.save(a);
    }

//    @After
//    public void cleanUp(){
//        userService.delete(a.getId());
//        userService = null;
//        assertNull(userService);
//    }




    @Test
    public void saveTest() {

        String pwd = "122333.0";

        assertEquals(pwd, a.getPassword());
    }

    @Test
    public void updateTest(){
        User kyo = userService.getUsersByName( "Rio").get(1);
        String newEmail = "Leave@gmail.com";
        kyo.setEmail(newEmail);
        userService.update(kyo);

        assertEquals(kyo.getEmail(), newEmail);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = userService.delete(6);

        assertEquals(1,expectedOfNum);
    }

    long toString(User b){
        return b.getId();
    }

    @Test
    public void getUsersTest(){
        List<User> all = userService.getUsers();
        int expectedOfNumber = 7;


//        for (User i : all){
//            System.out.println(i);
//        }

        Assert.assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getUserByNameTest(){
        String testName = "Hoan";
        User test = userService.getUsersByName(testName).get(0);
        System.out.println(test.getPets());

        assertEquals(testName, test.getFullName());
    }
}
