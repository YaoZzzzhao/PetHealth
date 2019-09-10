package com.ascending.training.repository;

import com.ascending.training.model.Dog;
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

public class DogDaoImpl implements DogDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private PetDaoImpl petDaoImpl = new PetDaoImpl();

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

        if(isSuccess) logger.debug(String.format("The dog %s was saved!",dog.toString()));

        return isSuccess;
    }

    @Override
    public boolean saveDog(Dog dog, long id){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Pet pet = petDaoImpl.dogGetPetById(id);
            dog.setPet(pet);
            session.save(dog);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage(),e);
        }

        if(isSuccess) logger.debug(String.format("The pet %s was saved!",dog.toString()));

        return isSuccess;
    }




    @Override
    public int update(Dog dog){
        boolean isSuccess = true;
        Transaction transaction = null;
        int count = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Pet p = petDaoImpl.dogGetPetById(dog.getPet().getId());
            dog.setPet(p);
            session.update(dog);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) {
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
    public List<Dog> getDogs(){
        String sql = "From Dog";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Dog> query = session.createQuery(sql);

            return query.list();
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public List<Dog> getDogsByName(String dogName){
        if (dogName == null ) return null;

        String sql = "From Dog dog where name = :name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Dog> query = session.createQuery(sql);
            query.setParameter("name",dogName);

//            Dog dog = query.uniqueResult();
//            logger.debug(dog.toString());

            return query.list();
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }
}

