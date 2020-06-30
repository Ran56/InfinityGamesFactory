package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Game;
import com.infinity.gamesfactory.util.HibernateUtil;
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
public class GameDaoImpl implements GameDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Game save(Game game) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(game);
            transaction.commit();
            session.close();
            return game;
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
    public List<Game> getGames() {
        String hql = "FROM Game g JOIN FETCH g.console";
        List<Game> games = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try{

            Query query = session.createQuery(hql);
            games = query.list();
            session.close();
        }
        catch(HibernateException e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return games;
    }

    @Override
    public Game getBy(Long id) {
        String hql = "FROM Game g JOIN FETCH g.console where g.id = :Id ";
        Session session = sessionFactory.openSession();
        try
        {
            Query<Game> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Game game = query.uniqueResult();
            session.close();
            return game;
        }
        catch (HibernateException e)
        {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(Game game) {
        Transaction transaction = null;
        String hql = "DELETE Game g where g.id = :Id";
        Session session = sessionFactory.openSession();

        int deletedCount = 0;
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("Id",game.getId());
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
    public Game update(Game game) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(game);
            transaction.commit();
            session.close();
            return game;
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
    public List<Game> getGamesEager() {
        return null;
    }

    @Override
    public Game getGameEagerBy(Long id) {
        String hql = "FROM Game g LEFT JOIN FETCH g.console where g.id =:Id";

        Session session = sessionFactory.openSession();
        try{
            Query<Game> query = session.createQuery(hql);
            query.setParameter("Id",id);
            Game game = query.uniqueResult();
            session.close();
            return game;
        }
        catch(Exception e)
        {
            logger.error("Fail to close session"+e);
            session.close();
            return null;
        }

    }

    @Override
    public Game getGameByName(String name) {
        String hql = "FROM Game e LEFT JOIN fetch e.console where e.name =:name";
        Session session = sessionFactory.openSession();

        try {
            Query<Game> query = session.createQuery(hql);
            query.setParameter("name", name);
            Game result = query.uniqueResult();
            session.close();
            return result;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public Game getGamesAndConsolesBy(String name) {
        return null;
    }

    @Override
    public List<Object[]> getCompaniesAndConsolesAndGames(String name) {
        return null;
    }
}
