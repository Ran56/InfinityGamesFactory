package com.infinity.gamesfactory.jdbc;

import com.infinity.gamesfactory.repository.ConsoleJDBCDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ConsoleDaoTest {

    private static Logger logger = LoggerFactory.getLogger(CompanyDaoTest.class);
    private ConsoleJDBCDao consoleJDBCDAO;
    private Console console;
    @Before
    public void setUP()
    {
        consoleJDBCDAO = new ConsoleJDBCDao();
        console = new Console();
    }

    @After
    public void tearDown()
    {
        consoleJDBCDAO = null;
    }

    @Test
    public void save()
    {

        console.setName("Nintendo Switch Lite");
        console.setPrice(199.99);
        console.setIssueTime("2019-09-20");
        console.setColor("gray");
        console.setDeveloper("Nintendo");
        console.setWhatIncluded("Nintendo Switch Lite system and Nintendo Switch AC adapter");

//        console.setWhatIncluded("Nintendo Switch™ Console"+", Nintendo Switch Dock"+", Joy‑Con™ (L) Neon Blue"
//        +", Joy‑Con™ (R) Neon Red"+", Joy‑Con™ Wrist Straps"+", Joy‑Con™ Grip"+", High Speed HDMI™ Cable"+", Nintendo Switch AC Adapter");

        assertEquals(1, consoleJDBCDAO.save(console));
        logger.info("Insert data succeed");

    }

    @Test
    public void delete()
    {

        console.setName("Nintendo Switch1");
        assertEquals(1,consoleJDBCDAO.delete(console.getName()));
        logger.info("Delete data succeed");

    }

    @Test
    public void update()
    {

        String oldName = "Nitendo Switch1";
        console.setName("Nintendo Switch");
        console.setPrice(299.99);
        console.setIssueTime("2017-03-03");
        console.setColor("blue and red");
        console.setDeveloper("Nintendo");
        console.setWhatIncluded("Nintendo Switch™ Console"+ ", Nintendo Switch Dock"+ ", Joy‑Con™ (L) Neon Blue"
                +", Joy‑Con™ (R) Neon Red"+ ", Joy‑Con™ Wrist Straps"+", Joy‑Con™ Grip"+ ", High Speed HDMI™ Cable"
                +", Nintendo Switch AC Adapter");

        assertEquals(1, consoleJDBCDAO.update(oldName,console));
        assertNotSame(oldName,console.getName());
        logger.info("Update data succeed");

    }

    @Test
    public void getConsoles()
    {

        assertEquals(2,consoleJDBCDAO.getConsoles().size());



    }






}
