package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.ApplicationBootstrap;
import com.infinity.gamesFactory.model.Company;
import com.infinity.gamesFactory.model.Console;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class ConsoleDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ConsoleDao consoleDao;
    private Console console = new Console();

    @Autowired
    private CompanyDAO companyDAO;
    private Company company = new Company();

    @Before
    public void setUp()
    {

        company.setName("Nintendo");
        company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                "Consumer electronics" + " "+
                "Social networking service" + " "+
                "Cloud computing" + " " +
                "Video games" + " "+
                "Internet");
        company.setDescription("is a video game company in Kyoto.");
        company.setLocation("Kyoto,Japan");
        company.setWebAddress("www.nintendo.com");
        companyDAO.save(company);



        console.setName("Nintendo Switch Lite");
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
        int expect = 1;
        Assert.assertEquals(expect,consoleDao.getConsoles().size());
    }

    @Test
    public void getConsolesEagerBy()
    {
        Console console1 = consoleDao.getConsoleEagerBy(console.getId());
        Assert.assertEquals(console.getName(),console1.getName());
    }

}
