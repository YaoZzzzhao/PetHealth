package com.ascending.training.controller;

import com.ascending.training.model.Cat;
import com.ascending.training.model.Pet;
import com.ascending.training.service.CatService;
import com.ascending.training.service.PetService;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/cat"})
public class CatController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private CatService catService;

    @Autowired private PetService petService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cat> getCats(){

        return catService.getCats();
    }

    @RequestMapping(params={"name"}, method =RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cat> getCatsByName(@RequestParam String name){

        return catService.getCatsByName(name);
    }

    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String create(@RequestBody Cat c, @PathVariable long id){
        String msg = "This record was created successfully!";
        boolean isSuccess = catService.saveCat(c,id);

        if(! isSuccess) msg = "Failed to create!";

        return msg;
    }

    @RequestMapping(method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@RequestBody Cat d){
        String msg = "This record was updated successfully!";
        int isSuccess = catService.update(d);
        if(isSuccess == 0) msg = "This record was not updated successfully!";
        return msg;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@PathVariable long id){
        String msg = "This record was deleted successfully!";
        int isSuccess = catService.delete(id);
        if(isSuccess == 0) msg ="Failed to delete!";
        return msg;
    }



}
