package com.infinity.gamesfactory.model;

import javax.persistence.*;

@Entity
@Table(name = "Consoles")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "consoleName")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "issueTime")
    private String issueTime;

    @Column(name = "color")
    private String color;

    @Column(name = "developer")
    private String developer;

    @Column(name = "whatIncluded")
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
        return price;
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
