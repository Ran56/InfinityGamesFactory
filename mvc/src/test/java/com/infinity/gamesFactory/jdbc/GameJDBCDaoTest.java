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
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotSame;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class GameJDBCDaoTest {
//
//    private static Logger logger = LoggerFactory.getLogger(GameJDBCDaoTest.class);
//    private GameJDBCDao gameJDBCDAO = new GameJDBCDao();
//    private Game game = new Game();
//    private CompanyJDBCDao companyJDBCDAO = new CompanyJDBCDao();
//    private  Company company = new Company();
//    private ConsoleJDBCDao consoleJDBCDAO = new ConsoleJDBCDao();
//    private Console console = new Console();
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
//        List<Company> companies = companyJDBCDAO.getCompanies();
//
//        console.setName("Nintendo Switch Lite");
//        console.setPrice(199.99);
//        console.setColor("gray");
//        console.setCompanyId(companies.get(0).getId());
//        console.setWhatIncluded("Nintendo Switch Lite system and Nintendo Switch AC adapter");
//        consoleJDBCDAO.save(console);
//
//        List<Console> consoles = consoleJDBCDAO.getConsoles();
//
//        game.setName("Super Mario Odyssey");
//        game.setPrice(59.99);
//        game.setGenre("Platformer, Action");
//        game.setPlayers("up to 2 players");
//        game.setConsoleId(consoles.get(0).getId());
//        game.setSupportedLanguages("Japanese, English, French, German, Italian, Spanish, Dutch, Russian, Chinese");
//
//        assertEquals(1, gameJDBCDAO.save(game));
//        logger.info("Insert data succeed");
//
//    }
//
//    @Test
//    public void stage2_getConsoles()
//    {
//        assertEquals(1,gameJDBCDAO.getGames().size());
//    }
//
//    @Test
//    public void stage3_delete()
//    {
//
//        game.setName("Super Mario Odyssey");
//        console.setName("Nintendo Switch Lite");
//        company.setName("Nintendo");
//
//        int result = gameJDBCDAO.delete(game.getName());
//        consoleJDBCDAO.delete(console.getName());
//        companyJDBCDAO.delete(company.getName());
//
//        assertEquals(1,result);
//        logger.info("Delete data succeed");
//
//    }
//
//
//}
