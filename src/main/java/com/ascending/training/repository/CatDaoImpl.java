package com.ascending.training.repository;

import com.ascending.training.model.Cat;
import org.slf4j.Logger;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    @Override
    public boolean update(Cat cat){
        boolean isSuccess = true;
        Transaction transaction = null;

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

        if(isSuccess == true) logger.debug(String.format("The cat %s was updated!", cat.toString()));

        return isSuccess;
    }

    @Override
    public boolean delete(long id){
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

        return deleteCount >=1 ? true : false;
    }

    @Override
    public List<Cat> getCat(){
        String sql = "From Cat";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Cat> query = session.createQuery(sql);

            return query.list();
        }
    }

    @Override
    public Cat getCatById(long id){
        if (id < 0 ) return null;

        String sql = "From Pet pet where id = :id1";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cat> query = session.createQuery(sql);
            query.setParameter("id1",id);

            Cat cat = query.uniqueResult();
            logger.debug(cat.toString());

            return cat;
        }
    }
}
