package com.infinity.gamesfactory.service;


import com.infinity.gamesfactory.model.Game;
import com.infinity.gamesfactory.repository.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameDao gameDao;

    Game save(Game game){return gameDao.save(game);}
    List<Game> getGames(){return gameDao.getGames();}
    Game getBy(Long id){return gameDao.getBy(id);}
    boolean delete(Game game){return gameDao.delete(game);}

    Game update(Game game){return gameDao.update(game);}
    boolean delete(String name){return gameDao.delete(name);}
    List<Game> getGamesEager(){return gameDao.getGamesEager();}
    Game getGameEagerBy(Long id){return gameDao.getGameEagerBy(id);}
    Game getGameByName(String name){return gameDao.getGameByName(name);}
    Game getGamesAndConsolesBy(String name){return gameDao.getGamesAndConsolesBy(name);}
    List<Object[]> getCompaniesAndConsolesAndGames(String name){return gameDao.getCompaniesAndConsolesAndGames(name);}



}
