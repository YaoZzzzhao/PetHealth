package com.ascending.training.controller;


import com.ascending.training.model.User;
import com.ascending.training.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUsers(){
        return userService.getUsers();
    }

//    @RequestMapping(value = "/{fullName}",method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public User getUserByName(@PathVariable String fullName){
//        return userService.getUserByName(fullName).get(0);
//    }

    @RequestMapping( method = RequestMethod.GET, params = {"name"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> getUsersByName(@RequestParam(value = "name") String n){
//        logger.format(String.format(">>>>>>>Param: %s" , p));
        return userService.getUsersByName(n);
//        return user;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String creatUser(@RequestBody User user){
        logger.warn(">>>>>>>User:" + user.toString());
        String msg = "The user has been created!";
        boolean isSuccess = userService.save(user);

        if(!isSuccess) msg = "The user was not created!";

        return msg;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String updateUser(@RequestBody User u){
        String msg = "This user was updated successfully!";
        int updatedCount = userService.update(u);
        if(updatedCount==0) msg = "The user was not updated.";

        return msg;
    }

    @RequestMapping(value="/{userId}",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteUser(@PathVariable long userId){
        String msg = "The user was deleted successfully!";
        int deletedCount = userService.delete(userId);
        if(deletedCount==0) msg = "The user was not deleted successfully!";

        return msg;
    }

//    @RequestMapping(value = "/header", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getHeader(@RequestHeader(name = "token") String token ){
//        return token;
//    }
//
//    @RequestMapping(value = "/body", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String getBody(@RequestBody String body){
//        return body;
//    }


}
