package com.ascending.training.Repository;

import com.ascending.training.model.Pet;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernatePetTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void mappingTest(){
        String hql = "FROM Pet";
        List<Pet> pet = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Pet> query = session.createQuery(hql);
            pet = query.list();

        }catch(Exception e){
            logger.error(e.getMessage());
        }

        Assert.assertNotNull(pet);
    }
}
