package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.model.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private ConsoleDao consoleDao = new ConsoleDaoImpl();
    private Console console = new Console();

    private CompanyDAO companyDAO = new CompanyDaoImpl();
    private Company company = new Company();

    private Game game = new Game();
    private GameDao gameDao = new GameDaoImpl();


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

        game.setName("Red And Blue And Yellow");
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
        int expect = 3;
        Assert.assertEquals(expect,gameDao.getGames().size());
    }



}
