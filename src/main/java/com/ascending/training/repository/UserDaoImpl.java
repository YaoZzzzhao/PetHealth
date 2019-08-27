package com.ascending.training.repository;

import com.ascending.training.model.Customer;
import com.ascending.training.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean save(Customer customer){
        Transaction transaction = null;
        boolean isSuccess = true;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }

        catch(Exception e){
            isSuccess = false;
            if(transaction!=null) transaction.rollback();
            logger.error(e.getMessage());
        }

        if(isSuccess) logger.debug(String.format("The customer %s was saved.", customer.toString()));

        return isSuccess;
    }

    @Override
    public int update(Customer customer){
        Transaction transaction = null;
        boolean isSuccess = true;
        int updateCount = 0;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
//            Query<Customer> query = session.createQuery(hql);

            transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
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
            logger.debug(String.format("The customer %s was updated.", customer.toString()));
        }

        return updateCount;
    }


    @Override
    public int delete(long userId){

//        String hql = "DELETE Customer where id = :userId1";

        int deletedCount = 0;
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            Customer customer = session.get(Customer.class, userId);
            session.delete(customer);

            deletedCount = 1;

            //Query<Customer> query = session.createQuery(hql);
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
    public List<Customer> getUsers(){
//        String hql = "select distinct user FROM Customer as user left join fetch user.pets as pet left join fetch pet.dogs left join fetch pet.cats order by user.id";
        String hql = "From User";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = session.beginTransaction();
        Query<Customer> query = session.createQuery(hql);
        List<Customer> customers = query.list();
        t.commit();
        return customers;
    }

    @Override
    public List<Customer> getUsersByName(String name){
        if(name == null) return null;

        logger.info(">>>>>>>>>> Name = " + name);
        String hql = "FROM User as user where user.fullName = :name";
//        left join fetch user.pets

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Customer> query = session.createQuery(hql);
            query.setParameter("name", name);

//            Customer user = query.uniqueResult();
//            logger.debug(user.toString());


            return query.list();
        }catch(Exception e){
            logger.debug(e.getMessage());
            return null;
        }
    }
}
