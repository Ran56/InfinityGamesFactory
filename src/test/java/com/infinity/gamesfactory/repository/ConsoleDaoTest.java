package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.model.Console;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleDaoTest {




    private Logger logger = LoggerFactory.getLogger(getClass());
    private ConsoleDao consoleDao = new ConsoleDaoImpl();
    private Console console = new Console();

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
        company.setWebAddress("www.aa112233.com");
        companyDAO.save(company);


        console.setName("Hellow");
        console.setCompany(company);
        consoleDao.save(console);


    }

    @After
    public void tearDown()
    {
        consoleDao.delete(console);
        companyDAO.delete(company);
    }

    @Test
    public void getConsoles()
    {

        int expect = 4;
        Assert.assertEquals(expect,consoleDao.getConsoles().size());


    }





}
