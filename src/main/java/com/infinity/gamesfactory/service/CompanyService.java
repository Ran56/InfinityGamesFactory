package com.infinity.gamesfactory.service;


import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.repository.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;

    Company save(Company company)
    {
        return companyDAO.save(company);
    }
    List<Company> getCompanies()
    {
        return companyDAO.getCompanies();
    }
    Company getBy(Long id)
    {
        return companyDAO.getBy(id);
    }
    boolean delete(Company company)
    {
        return companyDAO.delete(company);
    }
    Company update(Company company)
    {
        return companyDAO.update(company);
    }
    boolean delete(String name)
    {
        return companyDAO.delete(name);
    }
    List<Company> getCompaniesEager()
    {
        return companyDAO.getCompaniesEager();
    }
    Company getCompanyEagerBy(Long id)
    {
        return companyDAO.getCompanyEagerBy(id);
    }
    Company getCompanyByName(String name)
    {
        return companyDAO.getCompanyByName(name);
    }
    Company getCompaniesAndConsolesBy(String name)
    {
        return companyDAO.getCompaniesAndConsolesBy(name);
    }
    List<Object[]> getCompaniesAndConsolesAndGames(String name){return companyDAO.getCompaniesAndConsolesAndGames(name); }



}
