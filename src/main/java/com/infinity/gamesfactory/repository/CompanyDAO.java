package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;

import java.util.List;

public interface CompanyDAO {


    Company save(Company company);
    List<Company> getDepartments();
    Company getBy(Long id);
    boolean delete(Company company);

    Company update(Company company);
    boolean delete(String name);
    List<Company> getDepartmentsEager();
    Company getDepartmentEagerBy(Long id);
    Company getDepartmentByName(String name);
    Company getDepartmentAndEmployeesBy(String  name);
    List<Object[]> getDepartmentAndEmployeesAndAccounts(String name);



}
