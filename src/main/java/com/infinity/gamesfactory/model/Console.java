package com.infinity.gamesfactory.model;

import java.sql.Date;

public class Console {

    private long id;
    private String name;
    private double price;
    private String issueTime;
    private String color;
    private String developer;
    private String whatIncluded;



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

    public void setIssueTime(String issueTime)
    {
        this.issueTime = issueTime;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setDeveloper(String developer){this.developer = developer; }

    public void setWhatIncluded(String whatIncluded)
    {
        this.whatIncluded = whatIncluded;
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
        return this.price = price;
    }

    public String getIssueTime()
    {
        return issueTime;
    }

    public String getColor()
    {
        return color;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public String getWhatIncluded()
    {
        return whatIncluded;
    }








}
