//package com.infinity.gamesFactory.jdbc;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import java.util.List;
//
//import static java.lang.Double.valueOf;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotSame;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ConsoleJDBCDaoTest {
//
//    private static Logger logger = LoggerFactory.getLogger(CompanyJDBCDaoTest.class);
//    private ConsoleJDBCDao consoleJDBCDAO = new ConsoleJDBCDao();
//    private Console console = new Console();
//    private Company company = new Company();
//    private CompanyJDBCDao companyJDBCDAO = new CompanyJDBCDao();
//
//
//    @Test
//    public void stage1_save()
//    {
//        company = new Company();
//        company.setName("Nintendo");
//        company.setIndustry("Software development" + " "+"Computer hardware" + " "+
//                "Consumer electronics" + " "+
//                "Social networking service" + " "+
//                "Cloud computing" + " " +
//                "Video games" + " "+
//                "Internet");
//        company.setDescription("is a video game company in Kyoto.");
//        company.setLocation("Kyoto,Japan");
//        company.setWebAddress("www.nintendo.com");
//        companyJDBCDAO.save(company);
//
//        //List<Company> companies = companyJDBCDAO.getCompanies();
//
//        console.setName("Nintendo Switch Lite");
//        console.setPrice(199.99);
//        console.setColor("gray");
//
//       // console.setCompanyId(1L);
//        console.setWhatIncluded("Nintendo Switch Lite system and Nintendo Switch AC adapter");
//
//        assertEquals(1, consoleJDBCDAO.save(console));
//
//        logger.info("Insert data succeed");
//
//    }
//
//
//    @Test
//    public void stage2_getConsoles()
//    {
//
//        assertEquals(1,consoleJDBCDAO.getConsoles().size());
//
//    }
//
//
//    @Test
//    public void stage3_delete()
//    {
//        console.setName("Nintendo Switch Lite");
//        company.setName("Nintendo");
//        int result = consoleJDBCDAO.delete(console.getName());
//        companyJDBCDAO.delete(company.getName());
//        assertEquals(1,result);
//        logger.info("Delete data succeed");
//
//    }
//
//
//
//
//
//
//}
