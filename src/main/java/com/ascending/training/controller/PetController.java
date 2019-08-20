package com.ascending.training.controller;

import com.ascending.training.model.Pet;
import com.ascending.training.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/pet"})
public class PetController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PetService petService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<Pet> getPets(){
        return petService.getPets();
    }



}
