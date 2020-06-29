package com.infinity.gamesfactory.jdbc;

import java.sql.Date;

public class Console {

    private long id;
    private String name;
    private double price;
    private Date issueTime;
    private String color;
    private long companyId;
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

    public void setIssueTime(Date issueTime)
    {
        this.issueTime = issueTime;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setCompanyId(long companyId){this.companyId = companyId; }

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

    public Date getIssueTime()
    {
        return issueTime;
    }

    public String getColor()
    {
        return color;
    }

    public long getCompanyId()
    {
        return companyId;
    }

    public String getWhatIncluded()
    {
        return whatIncluded;
    }








}
