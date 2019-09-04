package com.ascending.training.controller;

import com.ascending.training.model.Dog;
import com.ascending.training.model.Pet;
import com.ascending.training.service.DogService;
import com.ascending.training.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/dog"})
public class DogController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private DogService dogService;

    @Autowired private PetService petService;


    @RequestMapping(value = "",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Dog> getDogs(){

        return dogService.getDogs();
    }

    @RequestMapping(params={"name"}, method =RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Dog> getDogsByName(@RequestParam String name){

        return dogService.getDogsByName(name);
    }

    @RequestMapping(value = "/{dogId}",method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String create(@RequestBody Dog d, @PathVariable long dogId){
        String msg = "This record was created successfully!";
        boolean isSuccess = dogService.saveDog(d,dogId);
        if(! isSuccess) msg = "Failed to create!";
        return msg;
    }

    @RequestMapping(method = RequestMethod.PUT,produces = {MediaType.APPLICATION_JSON_VALUE})
    public String update(@RequestBody Dog d){
        String msg = "This record was updated successfully!";
        Pet p = petService.dogGetPetById(d.getPet().getId());
        d.setPet(p);
        int isSuccess = dogService.update(d);
        if(isSuccess == 0) msg = "This record was not updated successfully!";
        return msg;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String delete(@PathVariable long id){
        String msg = "This record was deleted successfully!";
        int isSuccess = dogService.delete(id);
        if(isSuccess == 0) msg ="Failed to delete!";
        return msg;
    }

}
