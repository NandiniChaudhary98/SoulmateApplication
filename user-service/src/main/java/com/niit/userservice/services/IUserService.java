package com.niit.userservice.services;

import com.niit.userservice.config.UserDTo;
import com.niit.userservice.domain.User;
import com.niit.userservice.exception.UserAlreadyExistsException;
import com.niit.userservice.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    public void saveUser(UserDTo userDTo) throws UserAlreadyExistsException;
    public User loginCheck(String emailId, String password);
    public List<User> getAllUser();
    public boolean deleteUser(String emailId) throws UserNotFoundException;
    public User getUserById(String emailId) throws UserNotFoundException;
}
