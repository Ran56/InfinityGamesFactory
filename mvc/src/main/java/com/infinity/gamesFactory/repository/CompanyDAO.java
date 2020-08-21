package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.Company;

import java.util.List;

public interface CompanyDAO {


    Company save(Company company);
    List<Company> getCompanies();
    Company getBy(Long id);
    boolean delete(Company company);

    Company update(Company company);
    boolean delete(String name);
    List<Company> getCompaniesEager();
    Company getCompanyEagerBy(Long id);
    Company getCompanyByName(String name);
    Company getCompaniesAndConsolesBy(String name);
    List<Object[]> getCompaniesAndConsolesAndGames(String name);



}
