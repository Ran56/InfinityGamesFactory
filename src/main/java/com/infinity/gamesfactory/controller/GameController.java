package com.infinity.gamesfactory.controller;


import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.model.Game;
import com.infinity.gamesfactory.service.ConsoleService;
import com.infinity.gamesfactory.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private GameService gameService;
    @Autowired
    private ConsoleService consoleService;

    //      /game GET
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Game> getGames()
    {
        return gameService.getGames();
    }

    //      /game/1 GET
    @RequestMapping(value = "/{Id}",method = RequestMethod.GET)
    public Game getBy (@PathVariable("Id") Long id)
    {
        return gameService.getBy(id);
    }

    //      /game?name=Animal GET
    @RequestMapping(value = "",method = RequestMethod.GET,params = {"name"})
    public Game getByName(@RequestParam("name") String name)
    {
        return gameService.getGameByName(name);
    }

    //      /game/2?name=Cat PATCH
    @RequestMapping(value = "/{Id}",method = RequestMethod.PATCH)
    public Game updateGame(@PathVariable("Id") Long id, @RequestParam("name") String name)
    {
        Game game = gameService.getBy(id);
        game.setName(name);
        game = gameService.update(game);
        logger.debug("Updating the name of game");
        return game;
    }

    //      /game/1 POST
    @RequestMapping(value = "/{Id}",method = RequestMethod.POST)
    public Game create(@PathVariable("Id") Long id, @RequestBody Game game)
    {
        Console console = consoleService.getBy(id);
        game.setConsole(console);
        game = gameService.save(game);
        return game;
    }

    //      /game/12 DELETE
    @RequestMapping(value = "/{Id}",method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("Id") Long id)
    {
        Game game = gameService.getBy(id);
        return gameService.delete(game);
    }

}
