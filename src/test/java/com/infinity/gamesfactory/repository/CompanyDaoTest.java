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
        company.setName("Mt");
        company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                "Consumer electronics" + " "+
                "Social networking service" + " "+
                "Cloud computing" + " " +
                "Video games" + " "+
                "Internet");
        company.setDescription("this is mt");
        company.setLocation("Redmond, Washington");
        company.setWebAddress("www.aaa112233.com");
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

        int expectedNumOfComp = 4;
        List<Company> companies = companyDAO.getCompanies();
        Assert.assertEquals(expectedNumOfComp,companies.size());

    }










}
