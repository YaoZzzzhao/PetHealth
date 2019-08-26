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
import java.util.Set;

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

    @Override
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
    public List<Cat> getCats(){
        String sql = "From Cat xxxx";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Cat> query = session.createQuery(sql);

            return query.list();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

//    public String toString(){
//        return cat.age;
//    }

    @Override
    public List<Cat> getCatsByName(String catName){
        if (catName == null ) return null;

        String sql = "From Cat cat where name = :name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Cat> query = session.createQuery(sql);
            query.setParameter("name",catName);

//            Cat cat = query.uniqueResult();
//            logger.debug(cat.toString());

            return query.list();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }
}
