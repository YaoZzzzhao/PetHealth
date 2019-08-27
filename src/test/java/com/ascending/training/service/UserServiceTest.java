package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import com.ascending.training.model.Customer;
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
public class CustomerServiceTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    private Customer a;

    @Before
    public void init(){

        a = new Customer();
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

        customerService.save(a);
    }

    @After
    public void cleanUp(){
        customerService.delete(a.getId());
        customerService = null;
        assertNull(customerService);
    }




    @Test
    public void saveTest() {

        String pwd = "122333.0";

        assertEquals(pwd, a.getPassword());
    }

    @Test
    public void updateTest(){
        Customer kyo = customerService.getUsersByName( "Jaygee").get(0);
        String newEmail = "Leave@gmail.com";
        kyo.setEmail(newEmail);
        customerService.update(kyo);

        assertEquals(kyo.getEmail(), newEmail);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = customerService.delete(6);

        assertEquals(1,expectedOfNum);
    }

    long toString(Customer b){
        return b.getId();
    }

    @Test
    public void getUsersTest(){
        List<Customer> all = customerService.getUsers();
        int expectedOfNumber = 7;


//        for (Customer i : all){
//            System.out.println(i);
//        }

        Assert.assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getUserByNameTest(){
        String testName = "Hoan";
        Customer test = customerService.getUsersByName(testName).get(0);
        System.out.println(test.getPets());

        assertEquals(testName, test.getFullName());
    }
}
