package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.model.Console;

import java.util.List;

public interface ConsoleDao {

    Console save(Console console);
    List<Console> getConsoles();
    Console getBy(Long id);
    boolean delete(Console console);

    Console update(Console console);
    boolean delete(String name);
    List<Console> getConsolesEager();
    Console getConsoleEagerBy(Long id);
    Console getConsoleByName(String name);
    Console getConsolesAndGamesBy(String name);
    List<Object[]> getCompaniesAndConsolesAndGames(String name);








}
