package com.niit.userservice.controller;


import com.niit.userservice.domain.User;
import com.niit.userservice.exception.UserAlreadyExistsException;
import com.niit.userservice.exception.UserNotFoundException;
import com.niit.userservice.services.SecurityTokenGeneratorImpl;
import com.niit.userservice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/userService")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    SecurityTokenGeneratorImpl securityTokenGenerator;

//    @PostMapping("/register")
//    public ResponseEntity<?> saveTheUser(@RequestBody User user) throws UserAlreadyExistsException {
//        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
//    }
    @PostMapping("/login")
    public ResponseEntity<?> LoginOfUser(@RequestBody User user) throws UserNotFoundException {
        User result=userService.loginCheck(user.getEmailId(),user.getPassword());
        if(result!=null){
            Map<String,String> map=securityTokenGenerator.tokenGenerator(result);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User does not Exists",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllTheUsers(){
        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }
    @DeleteMapping("user/{emailId}")
    public ResponseEntity<?> deleteTheUser(@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(emailId),HttpStatus.OK);
    }
    @GetMapping("user/{emailId}")
    public ResponseEntity<?> getTheUserById(@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserById(emailId),HttpStatus.OK);
    }
}
