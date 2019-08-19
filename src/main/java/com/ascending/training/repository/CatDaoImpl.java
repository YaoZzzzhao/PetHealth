package com.ascending.training.repository;

import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;
import org.slf4j.Logger;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class CatDaoImpl implements CatDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Cat cat){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(cat);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess==true) logger.debug(String.format("The cat %s was saved!",cat.toString()));

        return isSuccess;
    }


    public boolean saveCat(Cat cat, Pet pet){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            cat.setPet(pet);
            session.save(cat);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess==true) logger.debug(String.format("The pet %s was saved!",cat.toString()));

        return isSuccess;
    }



    @Override
    public int update(Cat cat){
        boolean isSuccess = true;
        Transaction transaction = null;
        int count = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(cat);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) {
            count++;
            logger.debug(String.format("The cat %s was updated!", cat.toString()));
        }

        return count;
    }

    @Override
    public int delete(long id){
        String hql = "Delete from Cat where id = :id";

        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cat> query = session.createQuery(hql);
            query.setParameter("id",id);
            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            // session.delete(pet);
            transaction.commit();
        }
        catch(Exception e){
            // isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(deleteCount == 1) logger.debug(String.format("The cat %s was deleted!", id));

        return deleteCount;
    }

    @Override
    public List<Cat> getCat(){
        String sql = "From Cat";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Cat> query = session.createQuery(sql);

            return query.list();
        }
    }

//    public String toString(){
//        return cat.age;
//    }

    @Override
    public Cat getCatById(long id){
        if (id < 0 ) return null;

        String sql = "From Cat cat where id = :id1";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cat> query = session.createQuery(sql);
            query.setParameter("id1",id);



            Cat cat = query.uniqueResult();
            logger.debug(cat.toString());



            return cat;
        }
    }
}
