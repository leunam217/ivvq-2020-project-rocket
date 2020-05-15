package com.teamrocket.projetdevop.ivvqproject.service.impl;


import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShoppingCartRepository cartRepository;

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    @Transactional
    public User save(User user) {

        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));


        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) {
                throw new IllegalArgumentException("User with email "+ user.getEmail()+" already exists");
            }

           User savedUser = userRepository.save(user);
            //return  userRepository.save(user);

            // initial Cart
           ShoppingCart savedCart = cartRepository.save(new ShoppingCart(savedUser));
           savedUser.setCart(savedCart);

            return userRepository.save(savedUser);

    }

    @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail()).orElse(null);
       oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
       oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        return userRepository.save(oldUser);
    }


}
