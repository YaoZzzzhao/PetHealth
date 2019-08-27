package com.ascending.training.repository;

import com.ascending.training.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HibernateCustomerTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private Customer a;
    private CustomerDaoImpl userDaoImpl = new CustomerDaoImpl();


    @Before
    public void init(){
//        CustomerDao userDao = new CustomerDao();

        String fullname = "Jaygee";
        String pwd = "122333.0";
        String email = "Kyo@gmail.com";
        Date regisDate = new Date(119,7,9);
        String petType = "CAT";
        int petNum = 1;

        a = new Customer();
//        a.setId(30);
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
        userDaoImpl.delete(a.getId());
        userDaoImpl = null;
        assertNull(userDaoImpl);
    }


//    @Test
//    public void getUserTest() {
//        String hql = "FROM Customer";
//        List<Customer> users = null;
//
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Customer> query = session.createQuery(hql);
//            users = query.list();
//
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//
//        Assert.assertNotNull(users);
//    }

    @Test
    public void saveTest() {
        // long id = 2;


//        CustomerDaoImpl userDaoImpl = new CustomerDaoImpl();
        String pwd = "122333.0";

//        String

        assertEquals(pwd, a.getPassword());
    }

    @Test
    public void updateTest(){
        Customer kyo = userDaoImpl.getUsersByName("Jaygee").get(0);
        String newEmail = "Leave@gmail.com";
        kyo.setEmail(newEmail);
        userDaoImpl.update(kyo);

        assertEquals(kyo.getEmail(), newEmail);
    }

    @Test
    public void deleteTest(){
        int expectedOfNum = userDaoImpl.delete(2);

        assertEquals(1,expectedOfNum);
    }

    @Test
    public void getUsersTest(){
        List<Customer> all = userDaoImpl.getUsers();
        int expectedOfNumber = 2;

        assertEquals(all.size(),expectedOfNumber);
    }

    @Test
    public void getUserByNameTest(){
        String testName = "Jaygee";
        Customer test = userDaoImpl.getUsersByName("Jaygee").get(0);
        System.out.println(test.getPets());

        assertEquals(testName, test.getFullName());
    }


}
