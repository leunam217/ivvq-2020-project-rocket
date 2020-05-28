package com.teamrocket.projetdevop.ivvqproject.controller;


import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.security.JWT.JsonWebTokenProvider;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.requestresponse.AuthentificationForm;
import com.teamrocket.projetdevop.ivvqproject.requestresponse.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin
@RestController
public class UserController {

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @Autowired
    JsonWebTokenProvider jsonWebTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthentificationForm authentificationForm) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authentificationForm.getUsername(), authentificationForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jsonWebTokenProvider.generate(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findOne(userDetails.getUsername());
            System.out.println(jwt);
            return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getName(), user.getRole()));

        } catch (AuthenticationException e) {
            logger.info("Incorrect username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }
    }


    @PostMapping("/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.save(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
