package com.ascending.training.repository;

import com.ascending.training.model.Pet;
import org.slf4j.Logger;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import java.util.List;

public class PetDaoImpl implements PetDao{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Pet pet){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(pet);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess==true) logger.debug(String.format("The pet %s was saved!",pet.toString()));

        return isSuccess;
    }

    @Override
    public int update(Pet pet){
        boolean isSuccess = true;
        Transaction transaction = null;
        int count = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(pet);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess == true) {
            count++;
            logger.debug(String.format("The pet %s was updated!", pet.toString()));
        }

        return count;
    }

    @Override
    public int delete(long id){
        String hql = "Delete from Pet where id = :id1";

        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Pet> query = session.createQuery(hql);
            query.setParameter("id1",id);
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

        if(deleteCount == 1) logger.debug(String.format("The pet %s was deleted!", id));

        return deleteCount;
    }

    @Override
    public List<Pet> getPet(){
        String sql = "From Pet";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Pet> query = session.createQuery(sql);

            return query.list();
        }
    }

    @Override
    public Pet getPetById(long id){
        if (id < 0 ) return null;

        String sql = "From Pet pet where id = :id1";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Pet> query = session.createQuery(sql);
            query.setParameter("id1",id);

            Pet pet = query.uniqueResult();
            logger.debug(pet.toString());

//            final int[] p = new int[]{2};
//            p = new int[]{3};

            return pet;



        }
    }
}
