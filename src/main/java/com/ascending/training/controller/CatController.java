package com.ascending.training.controller;

import com.ascending.training.model.Cat;
import com.ascending.training.service.CatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController             // 申明这是controller
@RequestMapping(value = {"/cat"})           //设置路径
public class CatController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CatService catService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<Cat> getCats(){
        return catService.getCats();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cat> getCatsByName(@PathVariable String name){
        return catService.getCatsByName(name);
    }



}
