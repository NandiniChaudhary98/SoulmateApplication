package com.niit.userservice.services;


import com.niit.userservice.config.UserDTo;
import com.niit.userservice.domain.User;
import com.niit.userservice.exception.UserAlreadyExistsException;
import com.niit.userservice.exception.UserNotFoundException;
import com.niit.userservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserRepository userRepository;

    @RabbitListener(queues = "SoulmateUserQueue")
    @Override
    public void saveUser(UserDTo userDTo) throws UserAlreadyExistsException {

        User user=new User();

        String emailId=userDTo.getJsonObject().get("emailId").toString();
        String password=userDTo.getJsonObject().get("password").toString();

        if(userRepository.findById(emailId).isEmpty()){
            user.setEmailId(emailId);
            user.setPassword(password);
        }
        userRepository.save(user);
//        return user;

//       if(userRepository.findById(user.getEmailId()).isPresent()){
//           throw new UserAlreadyExistsException();
//       }
//       return userRepository.save(user);
    }

    public User loginCheck(String emailId, String password) {
        if(userRepository.findById(emailId).isPresent()){
            User user=userRepository.findById(emailId).get();
            if(user.getPassword().equals(password)){
                return user;
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(String emailId) throws UserNotFoundException {
        if(userRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        userRepository.deleteById(emailId);
        return true;
    }

    @Override
    public User getUserById(String emailId) throws UserNotFoundException {
        if(userRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(emailId).get();
    }
}
