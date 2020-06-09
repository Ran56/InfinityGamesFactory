package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CompanyDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private CompanyDAO companyDAO = new CompanyDaoImpl();
    private Company company = new Company();

    @Before
    public void setUp()
    {
        company.setName("Microsoft");
        company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                "Consumer electronics" + " "+
                "Social networking service" + " "+
                "Cloud computing" + " " +
                "Video games" + " "+
                "Internet" + " "+
                "Corporate venture capital");
        company.setDescription("this is microsoft");
        company.setLocation("Redmond, Washington");
        company.setWebAddress("www.microsoft.com");
        companyDAO.save(company);
    }

    @After
    public void tearDown()
    {
        companyDAO.delete(company);
    }


    @Test
    public void getCompanyTest()
    {

        int expectedNumOfComp = 1;
        List<Company> companies = companyDAO.getDepartments();
        Assert.assertEquals(expectedNumOfComp,companies.size());

    }










}
