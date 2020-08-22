package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.ApplicationBootstrap;
import com.infinity.gamesFactory.model.Company;
import com.infinity.gamesFactory.model.Console;
import com.infinity.gamesFactory.model.Game;
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
public class GameDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ConsoleDao consoleDao;
    private Console console = new Console();

    @Autowired
    private CompanyDAO companyDAO;
    private Company company = new Company();

    @Autowired
    private GameDao gameDao = new GameDaoImpl();
    private Game game = new Game();



    @Before
    public void setUp()
    {
        company = new Company();
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


        game.setName("Super Mario Odyssey");
        game.setConsole(console);
        gameDao.save(game);
    }

    @After
    public void tearDown()
    {
        gameDao.delete(game);
        consoleDao.delete(console);
        companyDAO.delete(company);
    }

    @Test
    public void getGames()
    {
        int expect = 1;
        Assert.assertEquals(expect,gameDao.getGames().size());
    }

    @Test
    public void getGamesEagerBy()
    {
        Game game1 = gameDao.getGameEagerBy(game.getId());
        Assert.assertEquals(game.getName(),game1.getName());
    }

}
