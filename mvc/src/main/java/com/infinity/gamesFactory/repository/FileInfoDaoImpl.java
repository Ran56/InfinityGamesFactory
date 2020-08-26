package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.Console;
import com.infinity.gamesFactory.model.FileInfo;
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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileInfoDaoImpl implements FileInfoDao{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public FileInfo save(FileInfo fileInfo) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try
        {
            transaction = session.beginTransaction();
            session.save(fileInfo);
            transaction.commit();
            session.close();
            return fileInfo;
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
    public List<FileInfo> getFileInfos() {
        String hql = "FROM FileInfo f JOIN FETCH f.user";
        List<FileInfo> fileInfos = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try{

            Query query = session.createQuery(hql);
            fileInfos = query.list();
            session.close();
        }
        catch(HibernateException e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return fileInfos;
    }

    @Override
    public FileInfo getBy(Long id) {
        String hql = "FROM FileInfo f JOIN FETCH f.user where f.id = :Id";
        Session session = sessionFactory.openSession();
        FileInfo fileInfo = new FileInfo();
        try
        {
            Query<FileInfo> query = session.createQuery(hql);
            query.setParameter("Id",id);
            fileInfo = query.uniqueResult();
            session.close();
            return fileInfo;
        }
        catch (HibernateException e)
        {
            logger.error("failure to retrieve data record",e);
            session.close();
            return null;
        }
    }

    @Override
    public boolean delete(FileInfo fileInfo) {
        Transaction transaction = null;
        String hql = "DELETE FileInfo f where f.id = :Id";
        Session session = sessionFactory.openSession();

        int deletedCount = 0;
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("Id",fileInfo.getId());
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
    public FileInfo update(FileInfo fileInfo) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.update(fileInfo);
            transaction.commit();
            session.close();
            return fileInfo;
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
    public boolean delete(String originalS3Key) {
        return false;
    }

    @Override
    public List<FileInfo> getFileInfosEager() {
        return null;
    }

    @Override
    public FileInfo getFileInfoEagerById(Long id) {
        String hql = "FROM FileInfo f LEFT JOIN FETCH f.user where f.id=:Id";
        FileInfo fileInfo= new FileInfo();
        Session session = sessionFactory.openSession();
        try{

            Query<FileInfo> query = session.createQuery(hql);
            query.setParameter("Id",id);
            fileInfo = query.uniqueResult();
            session.close();
        }
        catch(Exception e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return fileInfo;
    }


    @Override
    public List<FileInfo> getFileInfoByName(String uuidOrOriginalName, User user) {
        String hql = "FROM FileInfo f LEFT JOIN FETCH f.user where f.originalS3Key =:uuidOrOriginalName or f.uuidS3key =:uuidOrOriginalName and f.user =:user";
        Session session = sessionFactory.openSession();
        List<FileInfo> list = new ArrayList<>();
        try {
            Query<FileInfo> query = session.createQuery(hql);
            query.setParameter("uuidOrOriginalName", uuidOrOriginalName);
            query.setParameter("user", user);
            list = query.getResultList();
            session.close();
            return list;
        } catch (HibernateException e) {
            logger.error("failure to retrieve data record", e);
            session.close();
            return null;
        }
    }

    @Override
    public FileInfo getFileInfoByUser(User user,String uuidOrOriginalName) {
        String hql = "FROM FileInfo f LEFT JOIN FETCH f.user where f.user=:user and f.originalS3Key=:uuidOrOriginalName or f.uuidS3key=:uuidOrOriginalName";
        FileInfo fileInfo= new FileInfo();
        Session session = sessionFactory.openSession();
        try{

            Query<FileInfo> query = session.createQuery(hql);
            query.setParameter("user",user);
            query.setParameter("uuidOrOriginalName",uuidOrOriginalName);
            fileInfo = query.uniqueResult();
            session.close();
        }
        catch(Exception e)
        {
            logger.error("Fail to close session"+e);
            session.close();
        }
        return fileInfo;
    }
}
