package com.ascending.training.controller;

import com.ascending.training.model.Cat;
import com.ascending.training.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/cat"})
public class CatController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CatService catService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<Cat> getCats(){
        return catService.getCats();
    }



}
