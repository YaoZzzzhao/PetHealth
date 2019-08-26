package com.ascending.training.controller;

import com.ascending.training.model.Pet;
import com.ascending.training.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/pet"})
public class PetController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PetService petService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<Pet> getPets(){
        return petService.getPets();
    }

//    @RequestMapping(value = "/{xx}",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public String

    @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Pet> getPetsByName(@RequestParam String name){
        logger.warn(">>>>>>>>Param: %s" , name);
        return petService.getPetsByName(name);
    }

//    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})


    @RequestMapping(value="", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@RequestBody Pet p){
        String msg = "This pet was updated successfully!";
        int updatedCount = petService.update(p);
        if(updatedCount==0) msg = "The pet was not updated.";

        return msg;
    }

    @RequestMapping(value="/{petId}",method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@PathVariable long id){
        String msg = "This pet was deleted!";
        int deletedCount = petService.delete(id);
        if(deletedCount==0) msg = "This pet was not deleted!";
        return msg;
    }
}
