package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.ApplicationBootstrap;
import com.infinity.gamesFactory.model.Company;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class CompanyDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CompanyDAO companyDAO;
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
        int expectedNumOfComp = 5;
        List<Company> companies = companyDAO.getCompanies();
        Assert.assertEquals(expectedNumOfComp,companies.size());
    }


    @Test
    public void getConsolesEagerBy()
    {
        Company company1 = companyDAO.getCompanyEagerBy(company.getId());
        Assert.assertEquals(company.getName(),company1.getName());
    }

}
