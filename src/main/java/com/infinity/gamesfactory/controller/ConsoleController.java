package com.infinity.gamesfactory.controller;

import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.repository.ConsoleDao;
import com.infinity.gamesfactory.service.CompanyService;
import com.infinity.gamesfactory.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/console")
public class ConsoleController {

    @Autowired
    private ConsoleService consoleService;
    @Autowired
    private CompanyService companyService;

    //      /console GET
    @RequestMapping(value = "",method = RequestMethod.GET)
    public List<Console> getConsoles()
    {
        return consoleService.getConsoles();
    }

    //      /console/2 GET
    @RequestMapping(value = "/{Id}",method = RequestMethod.GET)
    public Console getBy (@PathVariable(name = "Id") Long id)
    {
        return consoleService.getBy(id);
    }

    //      /console?name=N GET
    @RequestMapping(value = "",method = RequestMethod.GET,params = {"name"})
    public Console getByName(@RequestParam(name = "name") String name)
    {
        return consoleService.getConsoleByName(name);
    }

    //      /console/6?name=UN PATCH
    @RequestMapping(value = "/{Id}",method = RequestMethod.PATCH)
    public Console updateConsole(@PathVariable(name = "Id") Long id,@RequestParam(name = "name") String name)
    {
        Console console = consoleService.getBy(id);
        console.setName(name);
        return consoleService.update(console);
    }

    //      /console/1 POST
    @RequestMapping(value = "/{Id}",method = RequestMethod.POST)
    public Console create(@PathVariable("Id") Long id, @RequestBody Console console)
    {
        Company company = companyService.getBy(id);
        console.setCompany(company);
        return consoleService.save(console);
    }

    //      /console/7 DELETE
    @RequestMapping(value = "/{Id}",method = RequestMethod.DELETE)
    public Boolean delete(@PathVariable("Id") Long id)
    {
        Console console = consoleService.getBy(id);
        return consoleService.delete(console);
    }

}
