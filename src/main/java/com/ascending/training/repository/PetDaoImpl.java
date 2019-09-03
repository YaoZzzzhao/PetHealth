package com.ascending.training.repository;

import com.ascending.training.model.Pet;
import com.ascending.training.model.User;
import org.slf4j.Logger;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

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

        if(isSuccess) logger.debug(String.format("The pet %s was saved!",pet.toString()));

        return isSuccess;
    }


    public boolean savePet(Pet pet, User user){
        boolean isSuccess = true;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            pet.setUser(user);
            session.save(pet);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null ) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The pet %s was saved!",pet.toString()));

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

        if(isSuccess) {
            count++;
            logger.debug(String.format("The pet %s was updated!", pet.toString()));
        }

        return count;
    }

    @Override
    public int delete(long id){
//        String hql = "Delete from Pet where id = :id1";

        int deleteCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            Query<Pet> query = session.createQuery(hql);
//            query.setParameter("id1",id);
            Pet pet = session.get(Pet.class, id);
            session.delete(pet);

            transaction = session.beginTransaction();
//            deleteCount = query.executeUpdate();
           // session.delete(pet);
            deleteCount = 1;
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
    public List<Pet> getPets(){
        String hql = "select distinct pet From Pet as pet left join fetch pet.dogs left join fetch pet.cats order by pet.id";
//        String hql = "From Pet order by id";
//        pet join fetch pet.cats p_c on pet.id = p_c.id join fetch pet.dogs p_d on pet.id = p_d.id


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Pet> query = session.createQuery(hql);

            return query.list();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Pet> getPetsByName(String petName){
        if (petName == null) return null;

        String hql = "From Pet pet where pet.name = :name";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Pet> query = session.createQuery(hql);
            query.setParameter("name",petName);

//            Pet pet = query.uniqueResult();
//            logger.debug(pet.toString());

            return query.list();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public Pet userGetPetById(long id){
        if (id <= 0) return null;

        String hql = "From Pet as pet left join fetch pet.user where pet.id = :id1";

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<Pet> query = session.createQuery(hql);
            query.setParameter("id1",id);

//            Pet pet = query.uniqueResult();
//            logger.debug(pet.toString());
            Pet p = query.uniqueResult();
            return p;
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public Pet dogGetPetById(long id){
        String hql = "From Pet as pet left join fetch pet.dogs as pd where pd.dogId = :id";
//        select distinct pet
//        String hql = "From Pet order by id";
//        pet join fetch pet.cats p_c on pet.id = p_c.id join fetch pet.dogs p_d on pet.id = p_d.id


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Pet> query = session.createQuery(hql);
            query.setParameter("id",id);

            return query.uniqueResult();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public Pet catGetPetById(long id){
        String hql = "select distinct pet From Pet as pet left join fetch pet.cats where pet.cats.cat_id = :id";
//        String hql = "From Pet order by id";
//        pet join fetch pet.cats p_c on pet.id = p_c.id join fetch pet.dogs p_d on pet.id = p_d.id


        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query <Pet> query = session.createQuery(hql);
            query.setParameter("id",id);

            return query.uniqueResult();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }
}
