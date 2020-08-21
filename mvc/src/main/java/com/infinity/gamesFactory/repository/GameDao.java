package com.infinity.gamesFactory.repository;

import com.infinity.gamesFactory.model.Game;

import java.util.List;

public interface GameDao {

    Game save(Game game);
    List<Game> getGames();
    Game getBy(Long id);
    boolean delete(Game game);

    Game update(Game game);
    boolean delete(String name);
    List<Game> getGamesEager();
    Game getGameEagerBy(Long id);
    Game getGameByName(String name);
    Game getGamesAndConsolesBy(String name);
    List<Object[]> getCompaniesAndConsolesAndGames(String name);



}
