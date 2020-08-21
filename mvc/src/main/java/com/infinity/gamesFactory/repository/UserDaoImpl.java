package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public User save(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return user;
        }
        catch (Exception e)
        {
            if(transaction!=null)
                transaction.rollback();
            logger.error("failure to insert record",e);
            session.close();
            return null;
        }

    }

    @Override
    public void delete(User user) {
        String hql = "DELETE User as u where u.id = :Id";
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery(hql);
            query.setParameter("Id", user.getId());
            query.executeUpdate();
            transaction.commit();
            session.close();
        }
        catch(HibernateException e)
        {
            if(transaction != null) transaction.rollback();
            session.close();
            logger.error("unable to delete record", e);
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try{
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            return user;
        }
        catch(Exception e)
        {
            if(transaction != null) transaction.rollback();
            logger.error("failure to update record",e);
            session.close();
            return null;
        }
    }

    @Override
    public User getById(Long id) {
        String hql = "FROM User u where u.id =:id";
        Session session = sessionFactory.openSession();
        try
        {
            Query<User> query = session.createQuery(hql);
            query.setParameter("id",id);
            User result = query.uniqueResult();
            session.close();
            return result;
        }
        catch(HibernateException e)
        {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String hql = "FROM User u where u.email =:email";
        Session session = sessionFactory.openSession();
        try
        {
            Query<User> query = session.createQuery(hql);
            query.setParameter("email",email);
            User result = query.uniqueResult();
            session.close();
            return result;
        }
        catch(HibernateException e)
        {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }    }

    @Override
    public List<User> getAllUsers() {
        String hql = "From User";
        Session session = sessionFactory.openSession();
        List<User> result = new ArrayList<>();

        try {
            Query query = session.createQuery(hql);
            result = query.list();
            session.close();
        }
        catch(HibernateException e)
        {
            logger.error("failure to retrieve data record",e);
            session.close();
        }
        return result;    }

    @Override
    public User getUserByName(String name) {
        String hql = "FROM User as u where u.name = :name";
        Session session = sessionFactory.openSession();
        try {
            Query<User> query = session.createQuery(hql);
            query.setParameter("name", name);
            User user = query.uniqueResult();
            session.close();
            return user;
        } catch (HibernateException e) {
            session.close();
            logger.error("unable to retrieve record", e);
            return null;
        }
    }

    @Override
    public User getUserByCredentials(String name, String password) {
        String hql = "From User u where u.name=:name and u.password=:password ";
        Session s = sessionFactory.openSession();
        User result = null;

        try {
            Query<User> query = s.createQuery(hql);
            query.setParameter("name",name);
            query.setParameter("password",password);
            result = query.uniqueResult();
            s.close();
        }
        catch(HibernateException e)
        {
            logger.error("session close exception try again...",e);
            s.close();
        }
        return result;
    }
}
