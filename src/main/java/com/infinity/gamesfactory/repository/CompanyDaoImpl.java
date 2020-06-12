package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;


public class CompanyDaoImpl implements CompanyDAO {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Company save(Company company) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
            session.close();
            return company;
        }
        catch(Exception e)
        {
            if(transaction != null)
            transaction.rollback();
            logger.info("Fail to insert data",e);
            session.close();
            return null;
        }
    }

    @Override
    public List<Company> getDepartments() {
        String hql = "from Company";
        List<Company> companies = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
       try{

        Query query = session.createQuery(hql);
        companies = query.list();
        session.close();
       }
       catch(Exception e)
       {
           logger.error("Fail to close session"+e);
           session.close();
       }
        return companies;
    }



    @Override
    public boolean delete(Company company) {
        Transaction transaction = null;
        String hql = "delete Company where id = :Id";
        Session session = HibernateUtil.getSessionFactory().openSession();

        int deletedCount = 0;
        try
        {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("Id",company.getId());
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
    public Company getBy(Long id) {
        return null;
    }

    @Override
    public Company update(Company company) {
        return null;
    }

    @Override
    public boolean delete(String name) {
        return false;
    }

    @Override
    public List<Company> getDepartmentsEager() {
        return null;
    }

    @Override
    public Company getDepartmentEagerBy(Long id) {
        return null;
    }

    @Override
    public Company getDepartmentByName(String name) {
        return null;
    }

    @Override
    public Company getDepartmentAndEmployeesBy(String name) {
        return null;
    }

    @Override
    public List<Object[]> getDepartmentAndEmployeesAndAccounts(String name) {
        return null;
    }
}
