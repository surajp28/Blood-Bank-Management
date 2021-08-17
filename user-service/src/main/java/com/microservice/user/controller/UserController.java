package com.microservice.user.controller;

import com.microservice.user.entity.User;
import com.microservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/donate")
    public String donate(String emailId, Integer pint){
        System.out.println("here");
        return userService.donate(emailId, pint);
    }

    @PostMapping("/receive")
    public String receive(@RequestBody String emailId, @RequestBody int pint){
        return userService.receive(emailId, pint);
    }

}
