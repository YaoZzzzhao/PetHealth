package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.junit4.statements.SpringRepeat;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class UserServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private User a = new User();

    @Before
    public void init(){
//        UserDao userDao = new UserDao();

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
        // long id = 2;


//        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String pwd = "122333.0";

//        String

        assertEquals(pwd, a.getPwd());
    }
}
