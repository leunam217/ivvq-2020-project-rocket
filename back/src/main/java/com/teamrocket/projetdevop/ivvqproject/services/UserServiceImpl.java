package com.teamrocket.projetdevop.ivvqproject.services;

import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.ShoppingCartRepository;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    /**
     * method to find one user
     * @param userId
     * @return
     */
    public User findOneUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User userFinal = user.get();
        return userFinal;
    }

    /**
     * method to find user's role
     * @param role
     * @return
     */
    public Collection<User> findByRole(String role) {
        return userRepository.findAllByRole(role);
    }

    /**
     * method to save user
     * @param user
     * @return
     */
    public User saveUser(User user) {
        try {
            User saveUser = userRepository.save(user);

            ShoppingCart shoppingCart = shoppingCartRepository.save(new ShoppingCart(saveUser));
            saveUser.setCart(shoppingCart);
            return userRepository.save(saveUser);
        } catch (Exception e)
        {
            throw new IllegalArgumentException("Error");
        }
    }

    /**
     * method to update user informations
     * @param user
     * @return
     */
    public User updateUser(User user) {
        Optional<User> oldUser = userRepository.findById(user.getId());
        User oldUser1 = oldUser.get();
        oldUser1.setName(user.getName());
        oldUser1.setEmail(user.getEmail());
        oldUser1.setAddress(user.getAddress());
        oldUser1.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(oldUser1);
    }

}
