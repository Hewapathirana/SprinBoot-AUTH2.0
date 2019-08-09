package com.codetech.oauth2.services;

import com.codetech.oauth2.dto.AuthResponse;
import com.codetech.oauth2.exceptions.UsernameAlreadyExistsException;
import com.codetech.oauth2.model.UserModel;
import com.codetech.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by A Majutharan.
 * Date: 7/3/2019
 * Time: 5:01 PM
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Transactional
    public AuthResponse userInfo(Long userId) {
        UserModel userModel = userRepository.getOne(userId);
        UserModel minUserInfo = new UserModel();
        minUserInfo.setId(userModel.getId());
        minUserInfo.setName(userModel.getName());
        minUserInfo.setUsername(userModel.getUsername());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserModel(minUserInfo);
        authResponse.setRole("USER");
        return authResponse;
    }

    public UserModel saveUser (UserModel newUser){
        try{
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());

            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }
    }



}
