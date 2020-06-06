package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class CompanyDaoTest {
    private static Logger logger = LoggerFactory.getLogger(CompanyDaoTest.class);
    private CompanyDao companyJDBCDAO;
    private  Company company;
    @Before
    public void setUP()
    {
        companyJDBCDAO = new CompanyDao();
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
            company.setDescription("a Japanese consumer electronics and video game company headquartered in Kyoto");
            company.setLocation("Kyoto");
            company.setWebAddress("https://www.nintendo.com/");

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
