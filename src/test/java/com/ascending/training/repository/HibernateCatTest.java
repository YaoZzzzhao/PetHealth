package com.ascending.training.repository;

import com.ascending.training.model.Cat;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateCatTest{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mappingTest(){
        String hql = "From Cat";
        List<Cat> cat =null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cat> query = session.createQuery(hql);
            cat = query.list();
        }catch(Exception e){
            logger.error(e.getMessage());
        }

        Assert.assertNotNull(cat);
    }
}
