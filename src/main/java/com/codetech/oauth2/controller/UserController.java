package com.codetech.oauth2.controller;

import com.codetech.oauth2.model.UserModel;
import com.codetech.oauth2.repository.UserRepository;
import com.codetech.oauth2.services.MapValidationErrorService;
import com.codetech.oauth2.services.UserService;
import com.codetech.oauth2.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import org.springframework.validation.BindingResult;

/**
 * Created by A Majutharan.
 * Date: 6/16/2019
 * Time: 12:29 PM
 */
@RestController
@RequestMapping(path = "/user-service")
public class UserController {
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;


    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/get-all-users")
    //@CrossOrigin(origins = "http://localhost:3000")
    public
    ResponseEntity<Iterable> getAllUsers(Principal principal) {

        //System.out.println("xxxxxxxxxx= " +principal.getName());
        Iterable<UserModel> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/view-user")
    public @ResponseBody ResponseEntity<Optional> viewUser(@RequestParam("id") Long id) {
        return ResponseEntity.ok(userRepository.findById(id));
    }

    @GetMapping(path = "/logout")
    public @ResponseBody ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.setAuthenticated(false);
        new SecurityContextLogoutHandler().logout(request,response,authentication);
        SecurityContextHolder.clearContext();
        request.logout();
        request.getSession().invalidate();
        return ResponseEntity.ok("successfully logout");
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserModel user,BindingResult result){
        // Validate passwords match

        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        System.out.println("registration called");


        UserModel newUser = userService.saveUser(user);

        return  new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
    }

}
