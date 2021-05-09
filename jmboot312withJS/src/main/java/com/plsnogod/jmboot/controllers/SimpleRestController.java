package com.plsnogod.jmboot.controllers;

import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleRestController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/all_users")
    public ResponseEntity<List<User>> showUsers(){
        List <User> users = userService.showAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable long id){
        return userService.getUserById(id);
    }


    @PostMapping(value = "/new_user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<User> updUser(@RequestBody User user){
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}