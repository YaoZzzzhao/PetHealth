package com.ascending.training.controller;

import com.ascending.training.model.Dog;
import com.ascending.training.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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



}
