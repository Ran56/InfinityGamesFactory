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

    public Console save(Console console){return consoleDao.save(console);}
    public List<Console> getConsoles(){return consoleDao.getConsoles();}
    public Console getBy(Long id){return consoleDao.getBy(id);}
    public boolean delete(Console console){return consoleDao.delete(console);}

    public Console update(Console console){return consoleDao.update(console);}
    public boolean delete(String name){return consoleDao.delete(name);}
    public List<Console> getConsolesEager(){return consoleDao.getConsolesEager();}
    public Console getConsoleEagerBy(Long id){return consoleDao.getConsoleEagerBy(id);}
    public Console getConsoleByName(String name){return consoleDao.getConsoleByName(name);}
    public Console getConsolesAndGamesBy(String name){return consoleDao.getConsolesAndGamesBy(name);}
    public List<Object[]> getCompaniesAndConsolesAndGames(String name){return consoleDao.getCompaniesAndConsolesAndGames(name);}




}
