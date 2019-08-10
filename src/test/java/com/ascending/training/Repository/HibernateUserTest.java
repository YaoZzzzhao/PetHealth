package com.ascending.training.Repository;

import com.ascending.training.jdbc.UserDao;
import com.ascending.training.model.User;
import com.ascending.training.repository.UserDaoImpl;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class HibernateUserTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private User a = new User();
    private UserDaoImpl userDaoImpl = new UserDaoImpl();


    @Before
    public void init(){
        UserDao userDao = new UserDao();

        String fullname = "Kyo";
        String pwd = "122333.0";
        String email = "Kyo@gmail.com";
        Date regisDate = new Date(119,7,9);
        String petType = "DOG";
        int petNum = 1;

        User a = new User();
        // a.setId(id);
        a.setName(fullname);
        a.setPwd(pwd);
        a.setDate(regisDate);
        a.setType(petType);
        a.setNum(petNum);
        a.setEmail(email);

//        userDaoImpl.save(a);
    }



    @Test
    public void getUserTest() {
        String hql = "FROM User";
        List<User> users = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            users = query.list();

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        Assert.assertNotNull(users);
    }

    @Test
    public void saveTest() {
        // long id = 2;


//        UserDaoImpl userDaoImpl = new UserDaoImpl();
        String pwd = "122333.0";

//        String

        Assert.assertEquals(pwd, a.getPwd());
    }
}
