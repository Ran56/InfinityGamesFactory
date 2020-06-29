package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.model.Game;
import com.infinity.gamesfactory.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Game save(Game game) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        String hql = "FROM Game";
        List<Game> games = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        String hql = "FROM Game g where g.id = :Id";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Game game = new Game();
        try
        {
            Query<Game> query = session.createQuery(hql);
            query.setParameter("Id",id);
            game = query.uniqueResult();
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
        Session session = HibernateUtil.getSessionFactory().openSession();

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
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(game);
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
        String hql = "FROM Game g LEFT JOIN FETCH g.console";
        Game game= new Game();
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            Query<Game> query = session.createQuery(hql);
            game = query.uniqueResult();
            session.close();
        }
        catch(Exception e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return game;
    }

    @Override
    public Game getGameByName(String name) {
        return null;
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
