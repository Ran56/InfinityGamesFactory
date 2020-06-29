package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ConsoleDaoImpl implements ConsoleDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Console save(Console console) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        String hql = "FROM Console";
        List<Console> consoles = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        String hql = "FROM Console c where c.id = :Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        Session session = HibernateUtil.getSessionFactory().openSession();

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
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(console);
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
        String hql = "FROM Console c LEFT JOIN FETCH c.company";
        Console console= new Console();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{

            Query<Console> query = session.createQuery(hql);
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
        return null;
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
