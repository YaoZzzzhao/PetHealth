package com.ascending.training.repository;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.User;
import org.junit.After;
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
public class HibernateUserTest {

    //Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Logger logger;
    private User a;
//    private UserDao userDaoImpl = new UserDaoImpl();
    @Autowired
    private UserDao userDaoImpl;


    @Before
    public void init(){
//        UserDao userDao = new UserDao();

        String fullname = "Kite";
        String pwd =     "123kkkk";
        String email = "Kite@gmail.com";
        Date regisDate = new Date(119,7,9);
        String petType = "CAT";
        int petNum = 1;

        a = new User();
        a.setId(30);
        a.setFullName(fullname);
        a.setPassword(pwd);
        a.setRegisDate(regisDate);
        a.setPetType(petType);
        a.setPetNum(petNum);
        a.setEmail(email);

        userDaoImpl.save(a);
    }

   @After
   public void cleanUp(){
        if(a.getFullName()!=null) {
            userDaoImpl.delete(a.getId());
        }
       userDaoImpl = null;
       assertNull(userDaoImpl);
   }

    @Test
    public void saveTest() {
        // long id = 2;


//        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String pwd = "123kkkk";

//        String

        assertEquals(pwd, a.getPassword());
    }

    @Test
    public void updateTest(){
        User kyo = userDaoImpl.getUsersByName("Rio").get(0);
        String newEmail = "gggggg";
        kyo.setPassword(newEmail);
        userDaoImpl.update(kyo);

        assertEquals(kyo.getPassword(), newEmail);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = userDaoImpl.delete(a.getId());

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getUsersTest(){
        List<User> all = userDaoImpl.getUsers();
        int expectedOfNumber = 7;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getUserByNameTest(){
        String testName = "Jaygee";
        User test = userDaoImpl.getUsersByName("Jaygee").get(0);
        System.out.println(test.getPets());

        assertEquals(testName, test.getFullName());
    }
}
