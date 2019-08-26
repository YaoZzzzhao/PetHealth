package com.ascending.training.controller;

import com.ascending.training.model.Dog;
import com.ascending.training.service.DogService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/dog"})
public class DogController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DogService dogService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<Dog> getDogs(){
        return dogService.getDogs();
    }

    @RequestMapping(params="{name}", method =RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Dog> getDogsByName(@RequestParam String name){

        return dogService.getDogsByName(name);
    }



}
