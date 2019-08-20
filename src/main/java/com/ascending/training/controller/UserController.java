package com.ascending.training.controller;


import com.ascending.training.model.User;
import com.ascending.training.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = "application/json")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
