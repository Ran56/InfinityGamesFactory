package com.infinity.gamesfactory.model;


import javax.persistence.*;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "gamesName")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "players")
    private String players;

    @Column(name = "releaseTime")
    private String releaseTime;

    @Column(name = "supportPlatform")
    private String supportPlatform;

    @Column(name = "developer")
    private String developer;

    @Column(name = "supportedLanguages")
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
