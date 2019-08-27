package com.ascending.training.repository;

import com.ascending.training.model.User;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
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

        if(isSuccess) logger.debug(String.format("The user %s was saved.", user.toString()));

        return isSuccess;
    }

    @Override
    public int update(User user){
        Transaction transaction = null;
        boolean isSuccess = true;
        int updateCount = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            Query<User> query = session.createQuery(hql);

            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
//            updateCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            isSuccess = false;
            if(transaction != null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) {
            updateCount ++;
            logger.debug(String.format("The user %s was updated.", user.toString()));
        }

        return updateCount;
    }


    @Override
    public int delete(long userId){

//        String hql = "DELETE User where id = :userId1";

        int deletedCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            User user = session.get(User.class, userId);
            session.delete(user);

            deletedCount = 1;

            //Query<User> query = session.createQuery(hql);
            //query.setParameter("userId1",userId);
//            query.setParameter("userName1", userName);

//            transaction = session.beginTransaction();
            //deletedCount = query.executeUpdate();
            transaction.commit();
        }
        catch(Exception e){
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        logger.debug(String.format("The user %s was deleted.", userId));

        return deletedCount ;             // Ternary Operaion:  value1 ? value2 : value3
    }

    @Override
    public List<User> getUsers(){
//        String hql = "select distinct user FROM User as user left join fetch user.pets as pet left join fetch pet.dogs left join fetch pet.cats order by user.id";
        String hql = "From User";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query<User> query = session.createQuery(hql);
        List<User> users = query.list();
        t.commit();
        return users;
    }

    @Override
    public List<User> getUsersByName(String name){
        if(name == null) return null;

        logger.info(">>>>>>>>>> Name = " + name);
        String hql = "FROM User as user where user.fullName = :name";
//        left join fetch user.pets

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("name", name);

//            User user = query.uniqueResult();
//            logger.debug(user.toString());


            return query.list();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }

//    @Override
    public User getUserByCredentials(String email, String password) {
        String hql = "FROM User as u where lower(u.email) = :email and u.password = :password";
        logger.debug(String.format("User email: %s, password: %s", email, password));
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email", email.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        }
    }
}
