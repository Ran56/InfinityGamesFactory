package com.infinity.gamesFactory.jdbc;

import org.junit.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CompanyJDBCDaoTest {
    private static Logger logger = LoggerFactory.getLogger(CompanyJDBCDaoTest.class);
    private CompanyJDBCDao companyJDBCDAO = new CompanyJDBCDao();
    private  Company company = new Company();
    private String name = "Nintendo Switch";
    private String updateName = "Nintendo";


    @Test
        public void stage1_save()
        {
//            Company company = new Company();
            company.setName(name);
            company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                    "Consumer electronics" + " "+
                    "Social networking service" + " "+
                    "Cloud computing" + " " +
                    "Video games" + " "+
                    "Internet");
            company.setDescription("is a video game company in Kyoto.");
            company.setLocation("Kyoto,Japan");
            company.setWebAddress("www.nintendo.com");

            assertEquals(1, companyJDBCDAO.save(company));
            logger.info("Insert data succeed");

        }



    @Test
    public void stage2_update()
        {
            company.setName(updateName);
            company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                    "Consumer electronics" + " "+
                    "Social networking service" + " "+
                    "Cloud computing" + " " +
                    "Video games" + " "+
                    "Internet");
            company.setDescription("is a video game company in Kyoto.");
            company.setLocation("Kyoto,Japan");
            company.setWebAddress("www.nintendo.com");
            assertEquals(1, companyJDBCDAO.update(name,company));
            assertNotSame(name,company.getName());
            logger.info("Update data succeed");

        }

    @Test
    public void stage3_getCompanies()
        {

            Assert.assertEquals(1,companyJDBCDAO.getCompanies().size());

        }
    @Test
    public void stage4_delete()
        {
        company.setName(updateName);

        Assert.assertEquals(1,companyJDBCDAO.delete(company.getName()));
        logger.info("Delete data succeed");
        }

}
