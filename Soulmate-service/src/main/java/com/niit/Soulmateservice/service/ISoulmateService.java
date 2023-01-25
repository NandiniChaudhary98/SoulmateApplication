package com.niit.Soulmateservice.service;

import com.niit.Soulmateservice.domain.SoulmateUser;
import com.niit.Soulmateservice.exception.UserAlreadyExistsException;
import com.niit.Soulmateservice.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ISoulmateService {
    public SoulmateUser saveUser(String emailId, String name, int age, String gender, String city,String password, MultipartFile file) throws UserAlreadyExistsException, IOException;
    public List<SoulmateUser> getAllUser();
    public boolean deleteUser(String emailId) throws UserNotFoundException;
    public SoulmateUser getUserById(String emailId) throws UserNotFoundException;

}
