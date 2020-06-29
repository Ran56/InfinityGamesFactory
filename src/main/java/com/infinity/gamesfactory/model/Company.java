package com.infinity.gamesfactory.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "industry")
    private String industry;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "webPageAddress")
    private String webAddress;

    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private Set<Console> consoleSet;



    public void setId(long id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setIndustry(String industry)
    {
        this.industry = industry;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setWebAddress(String webAddress)
    {
        this.webAddress = webAddress;
    }


    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getIndustry()
    {
        return industry;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLocation()
    {
        return location;
    }

    public String getWebAddress()
    {
        return webAddress;
    }







}
