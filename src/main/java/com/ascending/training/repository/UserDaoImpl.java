package com.ascending.training.repository;

import com.ascending.training.model.User;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.rowset.spi.TransactionalWriter;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(User user){
        Transaction transaction = null;
        boolean isSuccess = true;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The user %s was saved.",user.toString()));

        return isSuccess;
    }

    @Override
    public boolean update(User user){
        Transaction transaction = null;
        boolean isSuccess = true;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The user %s was updated.",user.toString()));

        return isSuccess;
    }


    @Override
    public boolean delete(int userId, String userName){

        String hql = "DELETE User where userId = :userId1 and userName = :userName1";

        int deletedCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = session.createQuery(hql);
            query.setParameter("userId1",userId);
            query.setParameter("userName1", userName);

            transaction = session.beginTransaction();
            deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

//        logger.debug(String.format("The user %s was deleted.", user.toString()));

        return deletedCount >=1 ? true : false;             // Ternary Operaion:  value1 ? value2 : value3
    }

    @Override
    public List<User> getUser(){
        String hql = "FROM User";
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Query<User> query = session.createQuery(hql);

            return query.list();
        }
    }

    @Override
    public User getUserById(int userId){
        if(userId < 0) return null;

        String hql = "FROM User as user where userId = :userId1";

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("userId1", userId);

            User user = query.uniqueResult();
            logger.debug(user.toString());


            return user;
        }
    }
}
