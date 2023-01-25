package com.niit.Soulmateservice.service;

import com.niit.Soulmateservice.config.UserDTO;
import com.niit.Soulmateservice.domain.SoulmateUser;
import com.niit.Soulmateservice.exception.UserAlreadyExistsException;
import com.niit.Soulmateservice.exception.UserNotFoundException;
import com.niit.Soulmateservice.repository.SoulmateRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class SoulmateService implements ISoulmateService {
    @Autowired
    SoulmateRepository soulmateRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;


    @Override
    public SoulmateUser saveUser(String emailId, String name, int age, String gender, String city,String password, MultipartFile file) throws UserAlreadyExistsException, IOException {
        if(soulmateRepository.findById(emailId).isPresent()){
            throw new UserAlreadyExistsException();
        }
        SoulmateUser soulmateUser=new SoulmateUser();
        soulmateUser.setEmailId(emailId);
        soulmateUser.setName(name);
        soulmateUser.setAge(age);
        soulmateUser.setGender(gender);
        soulmateUser.setCity(city);
        soulmateUser.setImage( new Binary(BsonBinarySubType.BINARY,file.getBytes()));

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("emailId",emailId);
        jsonObject.put("password",password);
        UserDTO userDTO=new UserDTO(jsonObject);
        rabbitTemplate.convertAndSend(directExchange.getName(),"user_routing",userDTO);

        return soulmateRepository.save(soulmateUser);
    }

    @Override
    public List<SoulmateUser> getAllUser() {
        return soulmateRepository.findAll();
    }

    @Override
    public boolean deleteUser(String emailId) throws UserNotFoundException {
        if(soulmateRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        soulmateRepository.deleteById(emailId);
        return true;
    }

    @Override
    public SoulmateUser getUserById(String emailId) throws UserNotFoundException {
        if(soulmateRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return soulmateRepository.findById(emailId).get();
    }
}
