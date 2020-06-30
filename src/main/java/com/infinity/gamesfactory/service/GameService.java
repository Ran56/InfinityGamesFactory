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

    public Game save(Game game){return gameDao.save(game);}
    public List<Game> getGames(){return gameDao.getGames();}
    public Game getBy(Long id){return gameDao.getBy(id);}
    public boolean delete(Game game){return gameDao.delete(game);}

    public Game update(Game game){return gameDao.update(game);}
    public boolean delete(String name){return gameDao.delete(name);}
    public List<Game> getGamesEager(){return gameDao.getGamesEager();}
    public Game getGameEagerBy(Long id){return gameDao.getGameEagerBy(id);}
    public Game getGameByName(String name){return gameDao.getGameByName(name);}
    public Game getGamesAndConsolesBy(String name){return gameDao.getGamesAndConsolesBy(name);}
    public List<Object[]> getCompaniesAndConsolesAndGames(String name){return gameDao.getCompaniesAndConsolesAndGames(name);}



}
