package com.infinity.gamesFactory.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static java.lang.Double.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ConsoleJDBCDaoTest {

    private static Logger logger = LoggerFactory.getLogger(CompanyJDBCDaoTest.class);
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
        console.setColor("gray");
        console.setCompanyId(1);
        console.setWhatIncluded("Nintendo Switch Lite system and Nintendo Switch AC adapter");

        assertEquals(1, consoleJDBCDAO.save(console));
        logger.info("Insert data succeed");

    }

    @Test
    public void delete()
    {
        console.setName("NS1");
        assertEquals(1,consoleJDBCDAO.delete(console.getName()));
        logger.info("Delete data succeed");

    }

    @Test
    public void update()
    {

        String oldName = "Nitendo Switch1";
        console.setName("N");
        console.setPrice(valueOf(299.99));
        console.setColor("blue and red");
        console.setCompanyId(3);
        console.setWhatIncluded("N");

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
