package com.infinity.gamesFactory.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "Games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "players")
    private String players;

    @Column(name = "releaseTime")
    private Date releaseTime;

    @Column(name = "supportedLanguages")
    private String supportedLanguages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "console_id")
    @JsonIgnore
    private Console console;

    public void setId(long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public void setGenre(String genre){ this.genre = genre; }

    public void setPlayers(String players){this.players = players; }

    public void setReleaseTime(Date releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public void setConsole(Console console){this.console = console; }

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

    public BigDecimal getPrice()
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

    public String getSupportedLanguages(){return supportedLanguages;}

    public Console getConsole(){
        if(this.console==null)
            return null;
            return this.console;
    }
}
