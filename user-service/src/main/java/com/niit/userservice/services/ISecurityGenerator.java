package com.niit.userservice.services;

import com.niit.userservice.domain.User;

import java.util.Map;

public interface ISecurityGenerator {
    public Map<String,String> tokenGenerator(User user);

}
