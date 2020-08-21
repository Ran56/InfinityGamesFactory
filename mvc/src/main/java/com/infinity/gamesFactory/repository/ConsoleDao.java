package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.Console;

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
