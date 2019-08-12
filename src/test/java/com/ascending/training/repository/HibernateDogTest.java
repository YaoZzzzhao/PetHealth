package com.ascending.training.Repository;

import com.ascending.training.model.Dog;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateDogTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mappingTest(){
        String hql = "From Dog";
        List<Dog> dog = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Dog> query = session.createQuery(hql);
            dog = query.list();
        }catch(Exception e){
            logger.error(e.getMessage());
        }

        Assert.assertNotNull(dog);
    }
}
