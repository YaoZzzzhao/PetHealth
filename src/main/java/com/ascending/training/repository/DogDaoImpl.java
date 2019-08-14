package com.ascending.training.repository;

import com.ascending.training.model.Dog;
import com.ascending.training.model.Pet;
import com.ascending.training.model.User;
import org.slf4j.Logger;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DogDaoImpl implements DogDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Dog dog){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(dog);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess==true) logger.debug(String.format("The dog %s was saved!",dog.toString()));

        return isSuccess;
    }


    public boolean saveDog(Dog dog, User user){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            dog.setUser(user);
            session.save(dog);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess==true) logger.debug(String.format("The pet %s was saved!",dog.toString()));

        return isSuccess;
    }




    @Override
    public int update(Dog dog){
        boolean isSuccess = true;
        Transaction transaction = null;
        int count = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(dog);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess == true) {
            count ++;
            logger.debug(String.format("The dog %s was updated!", dog.toString()));
        }

        return count;
    }

    @Override
    public int delete(long id){
        String hql = "Delete from Dog where id = :id";

        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Dog> query = session.createQuery(hql);
            query.setParameter("id",id);
            transaction = session.beginTransaction();
            deleteCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            // isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(deleteCount == 1) logger.debug(String.format("The dog %s was deleted!", id));

        return deleteCount;
    }

    @Override
    public List<Dog> getDog(){
        String sql = "From Dog";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Dog> query = session.createQuery(sql);

            return query.list();
        }
    }

    @Override
    public Dog getDogById(long id){
        if (id < 0 ) return null;

        String sql = "From Dog dog where id = :id1";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Dog> query = session.createQuery(sql);
            query.setParameter("id1",id);

            Dog dog = query.uniqueResult();
            logger.debug(dog.toString());

            return dog;
        }
    }
}

