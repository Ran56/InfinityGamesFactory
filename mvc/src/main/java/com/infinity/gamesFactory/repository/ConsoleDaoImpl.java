package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.Console;
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
public class ConsoleDaoImpl implements ConsoleDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Console save(Console console) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(console);
            transaction.commit();
            session.close();
            return console;
        }
        catch (Exception e)
        {
            if(transaction != null)
                transaction.rollback();
            logger.info("Fail to insert data",e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Console> getConsoles() {
        String hql = "FROM Console c JOIN FETCH c.company";
        List<Console> consoles = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try{

            Query query = session.createQuery(hql);
            consoles = query.list();
            session.close();
        }
        catch(HibernateException e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return consoles;
    }

    @Override
    public Console getBy(Long id) {
        String hql = "FROM Console c JOIN FETCH c.company where c.id = :Id";
        Session session = sessionFactory.openSession();
        Console console = new Console();
        try
        {
            Query<Console> query = session.createQuery(hql);
            query.setParameter("Id",id);
            console = query.uniqueResult();
            session.close();
            return console;
        }
        catch (HibernateException e)
        {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(Console console) {
        Transaction transaction = null;
        String hql = "DELETE Console c where c.id = :Id";
        Session session = sessionFactory.openSession();

        int deletedCount = 0;
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("Id",console.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            session.close();
            return deletedCount >= 1 ? true : false;
        }
        catch (HibernateException e)
        {
            if(transaction != null)
                transaction.rollback();
            logger.info("Fail to delete data");
            session.close();
        }
        return false;
    }

    @Override
    public Console update(Console console) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(console);
            transaction.commit();
            session.close();
            return console;
        }
        catch(Exception e)
        {
            if(transaction != null)
                transaction.rollback();
            logger.info("Fail to update data",e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(String name) {
        return false;
    }

    @Override
    public List<Console> getConsolesEager() {
        return null;
    }

    @Override
    public Console getConsoleEagerBy(Long id) {
        String hql = "FROM Console c LEFT JOIN FETCH c.company where c.id=:Id";
        Console console= new Console();
        Session session = sessionFactory.openSession();
        try{

            Query<Console> query = session.createQuery(hql);
            query.setParameter("Id",id);
            console = query.uniqueResult();
            session.close();
        }
        catch(Exception e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return console;
    }

    @Override
    public Console getConsoleByName(String name) {
        String hql = "FROM Console e LEFT JOIN FETCH e.company where e.name =:name";
        Session session = sessionFactory.openSession();

        try {
            Query<Console> query = session.createQuery(hql);
            query.setParameter("name", name);
            Console result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Console getConsolesAndGamesBy(String name) {
        return null;
    }

    @Override
    public List<Object[]> getCompaniesAndConsolesAndGames(String name) {
        return null;
    }
}
