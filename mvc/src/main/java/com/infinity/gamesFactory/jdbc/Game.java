package com.infinity.gamesFactory.jdbc;

import java.sql.Date;

public class Game {


    private long id;
    private String name;
    private double price;
    private String genre;
    private String players;
    private Date releaseTime;
    private long consoleId;
    private String supportedLanguages;


    public void setId(long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setGenre(String genre){ this.genre = genre; }

    public void setPlayers(String players){this.players = players; }

    public void setReleaseTime(Date releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public void setConsoleId(long consoleId){this.consoleId = consoleId; }

    public void setSupportedLanguages(String supportedLanguages)
    {
        this.supportedLanguages = supportedLanguages;
    }



    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public String getGenre()
    {
        return genre;
    }

    public String getPlayers()
    {
        return players;
    }

    public Date getReleaseTime()
    {
        return releaseTime;
    }

    public long getConsoleId()
    {
        return consoleId;
    }

    public String getSupportedLanguages(){return supportedLanguages;}


}
