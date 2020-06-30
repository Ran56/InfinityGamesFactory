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

    public Company save(Company company)
    {
        return companyDAO.save(company);
    }
    public List<Company> getCompanies()
    {
        return companyDAO.getCompanies();
    }
    public Company getBy(Long id)
    {
        return companyDAO.getBy(id);
    }
    public boolean delete(Company company)
    {
        return companyDAO.delete(company);
    }
    public Company update(Company company)
    {
        return companyDAO.update(company);
    }
    public boolean delete(String name)
    {
        return companyDAO.delete(name);
    }
    public List<Company> getCompaniesEager()
    {
        return companyDAO.getCompaniesEager();
    }
    public Company getCompanyEagerBy(Long id)
    {
        return companyDAO.getCompanyEagerBy(id);
    }
    public Company getCompanyByName(String name)
    {
        return companyDAO.getCompanyByName(name);
    }
    public Company getCompaniesAndConsolesBy(String name)
    {
        return companyDAO.getCompaniesAndConsolesBy(name);
    }
    public List<Object[]> getCompaniesAndConsolesAndGames(String name){return companyDAO.getCompaniesAndConsolesAndGames(name); }



}
