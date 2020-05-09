package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;


    public User findOneUser(String userEmail) {

        return userRepository.findByEmail(userEmail);
    }


    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    public User saveUser(User user) {

            User saveUser = userRepository.save(user);

            ShoppingCart shoppingCart = shoppingCartRepository.save(new ShoppingCart(saveUser));
            saveUser.setCart(shoppingCart);

            return userRepository.save(saveUser);
    }


   public User updateUser(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
       oldUser.setName(user.getName());
       oldUser.setEmail(user.getEmail());
       oldUser.setAddress(user.getAddress());
       oldUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(oldUser);
    }

}
