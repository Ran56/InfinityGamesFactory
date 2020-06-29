package com.infinity.gamesfactory.jdbc;

import java.util.Date;

public class Company {

        private long id;
        private String name;
        private String industry;
        private String description;
        private String location;
        private String webAddress;


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
