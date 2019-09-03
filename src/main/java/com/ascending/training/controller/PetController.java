package com.ascending.training.controller;

import com.ascending.training.model.Pet;
import com.ascending.training.model.User;
import com.ascending.training.model.View;
import com.ascending.training.service.PetService;
import com.ascending.training.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    @JsonView(View.Pet.class)
    public List<Pet> getPets(){
        return petService.getPets();
    }

    @RequestMapping(value = "/withAllInfo", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @JsonView(View.CatNDog.class)
    public List<Pet> getPetsWithAllInfo(){
        return petService.getPets();
    }

//    @RequestMapping(value = "/{xx}",method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
//    public String

    @RequestMapping(params = {"name"}, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @JsonView(View.Pet.class)
    public List<Pet> getPetsByName(@RequestParam String name){
        logger.warn(">>>>>>>>Param: %s" , name);
        return petService.getPetsByName(name);
    }

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String create(@RequestBody Pet p, @PathVariable long ownerId){
        String msg = "The pet was created!";
        User u =userService.getUserById(ownerId);
        boolean isSuccess = petService.saveP(p,u);

        if(! isSuccess) msg = "The pet was not created successfully!";
        return msg;
    }


    @RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@RequestBody Pet p){
        User owner = petService.userGetPetById(p.getId()).getUser();
        String msg = "This pet was updated successfully!";
        p.setUser(owner);
        int updatedCount = petService.update(p);
        if(updatedCount==0) msg = "The pet was not updated.";

        return msg;
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@PathVariable long id){
        String msg = "This pet was deleted!";
        int deletedCount = petService.delete(id);
        if(deletedCount==0) msg = "This pet was not deleted!";
        return msg;
    }
}
