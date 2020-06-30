package com.infinity.gamesfactory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "Consoles")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "issueTime")
    private Date issueTime;

    @Column(name = "color")
    private String color;

    @Column(name = "whatIncluded")
    private String whatIncluded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "console", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Game> gameSet;


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

    public void setIssueTime(Date issueTime)
    {
        this.issueTime = issueTime;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public void setWhatIncluded(String whatIncluded)
    {
        this.whatIncluded = whatIncluded;
    }

    public void setCompany(Company company){ this.company = company; }


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

    public Date getIssueTime()
    {
        return issueTime;
    }

    public String getColor()
    {
        return color;
    }

    public String getWhatIncluded()
    {
        return whatIncluded;
    }

    public Company getCompany(){
        if(this.company ==null)
        return null;
        return this.company;
    }


}
