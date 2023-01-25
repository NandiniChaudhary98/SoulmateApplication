package com.niit.Soulmateservice.controller;


import com.niit.Soulmateservice.domain.SoulmateUser;
import com.niit.Soulmateservice.exception.UserAlreadyExistsException;
import com.niit.Soulmateservice.exception.UserNotFoundException;
import com.niit.Soulmateservice.service.SoulmateService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/soulmateService")
public class SoulmateController {
    @Autowired
    SoulmateService soulmateService;

    @PostMapping("/register")
    public ResponseEntity<?> saveTheUser(@RequestParam String emailId,@RequestParam String name,@RequestParam int age, @RequestParam String gender, @RequestParam String city, @RequestParam String password, @RequestParam MultipartFile file) throws UserAlreadyExistsException, IOException {
        return new ResponseEntity<>(soulmateService.saveUser(emailId,name,age,gender,city,password,file), HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllTheUsers(){
        return new ResponseEntity<>(soulmateService.getAllUser(),HttpStatus.OK);
    }
    @DeleteMapping("user/{emailId}")
    public ResponseEntity<?> deleteTheUser(@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(soulmateService.deleteUser(emailId),HttpStatus.OK);
    }
    @GetMapping("user/{emailId}")
    public ResponseEntity<?> getTheUserById(@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(soulmateService.getUserById(emailId),HttpStatus.OK);
    }
}
