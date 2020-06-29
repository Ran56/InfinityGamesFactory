package com.infinity.gamesfactory.service;


import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.repository.ConsoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsoleService {

    @Autowired
    private ConsoleDao consoleDao;

    Console save(Console console){return consoleDao.save(console);}
    List<Console> getConsoles(){return consoleDao.getConsoles();}
    Console getBy(Long id){return consoleDao.getBy(id);}
    boolean delete(Console console){return consoleDao.delete(console);}

    Console update(Console console){return consoleDao.update(console);}
    boolean delete(String name){return consoleDao.delete(name);}
    List<Console> getConsolesEager(){return consoleDao.getConsolesEager();}
    Console getConsoleEagerBy(Long id){return consoleDao.getConsoleEagerBy(id);}
    Console getConsoleByName(String name){return consoleDao.getConsoleByName(name);}
    Console getConsolesAndGamesBy(String name){return consoleDao.getConsolesAndGamesBy(name);}
    List<Object[]> getCompaniesAndConsolesAndGames(String name){return consoleDao.getCompaniesAndConsolesAndGames(name);}




}
