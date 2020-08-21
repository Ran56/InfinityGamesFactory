package com.infinity.gamesFactory.controller;

import com.infinity.gamesFactory.model.Company;
import com.infinity.gamesFactory.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CompanyService companyService;

    //      /company GET
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Company> getCompanies()
    {
        return companyService.getCompanies();
    }

    //      /company/1 GET
    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Company getBy(@PathVariable(name = "Id") Long id)
    {
        return companyService.getBy(id);
    }

    //      /company?name=AMZ GET
    @RequestMapping(value = "",method = RequestMethod.GET,params = {"name"})
    public Company getByName(@RequestParam(name = "name") String name)
    {
        return companyService.getCompanyByName(name);
    }

    //      /company/2?name=HH PATCH
    @RequestMapping(value = "/{Id}",method = RequestMethod.PATCH)
    public Company updateCompany (@PathVariable(name = "Id") Long id, @RequestParam("name") String name)
    {
        logger.debug("Updating name of company " + id);
        Company company = companyService.getBy(id);
        company.setName(name);
        company = companyService.update(company);
        return company;
    }

    //      /company POST
    @RequestMapping(value ="",method = RequestMethod.POST)
    public Company create(@RequestBody Company company)
    {
        return companyService.save(company);
    }

    //      /company/5 DELETE
    @RequestMapping(value = "/{Id}",method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable(name = "Id") Long id)
    {
        Company company = companyService.getBy(id);
        Boolean result = companyService.delete(company);
        return result;
    }

}
