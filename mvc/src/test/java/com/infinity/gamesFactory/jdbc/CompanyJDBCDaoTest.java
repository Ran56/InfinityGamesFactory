package com.infinity.gamesFactory.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CompanyJDBCDaoTest {
    private static Logger logger = LoggerFactory.getLogger(CompanyJDBCDaoTest.class);
    private CompanyJDBCDao companyJDBCDAO;
    private  Company company;
    @Before
    public void setUP()
    {
        companyJDBCDAO = new CompanyJDBCDao();
        company = new Company();
    }

    @After
    public void tearDown()
    {
        companyJDBCDAO = null;
    }

    @Test
        public void save()
        {

            Company company = new Company();
            company.setName("Mi");
            company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                    "Consumer electronics" + " "+
                    "Social networking service" + " "+
                    "Cloud computing" + " " +
                    "Video games" + " "+
                    "Internet");
            company.setDescription("this is mi");
            company.setLocation("Redmond, Washington");
            company.setWebAddress("");

            assertEquals(1, companyJDBCDAO.save(company));
            logger.info("Insert data succeed");

        }

    @Test
        public void delete()
        {

            company.setName("Nintendo");
            assertEquals(1,companyJDBCDAO.delete(company.getName()));
            logger.info("Delete data succeed");

        }

    @Test
        public void update()
        {

            String oldName = "Nintendo1";
            company.setName("Nintendo");
            company.setIndustry("Video game industry"+" "+"Consumer electronics");
            company.setDescription("this is a technological company");
            company.setLocation("");
            company.setWebAddress("");

            assertEquals(1, companyJDBCDAO.update(oldName,company));
            assertNotSame(oldName,company.getName());
            logger.info("Update data succeed");

        }

    @Test
        public void getCompanies()
        {

            assertEquals(2,companyJDBCDAO.getCompanies().size());



        }

}
