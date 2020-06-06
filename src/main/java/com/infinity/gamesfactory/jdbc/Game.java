package com.infinity.gamesfactory.jdbc;

import java.util.Date;

public class Game {


    private long id;
    private String name;
    private double price;
    private String genre;
    private String players;
    private String releaseTime;
    private String supportPlatform;
    private String developer;
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

    public void setReleaseTime(String releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public void setSupportPlatform(String supportPlatform)
    {
        this.supportPlatform = supportPlatform;
    }

    public void setDeveloper(String developer){this.developer = developer; }

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

    public String getReleaseTime()
    {
        return releaseTime;
    }

    public String getSupportPlatform()
    {
        return supportPlatform;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public String getSupportedLanguages(){return supportedLanguages;}


}
