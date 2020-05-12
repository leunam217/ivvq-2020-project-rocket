package com.teamrocket.projetdevop.ivvqproject.services;


import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
/**import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;


public class UserServiceTest {


   UserServiceImpl userService = new UserServiceImpl();

    @MockBean
    UserRepository userRepository;


    @Test
    public void findByEmail()
    {
        userService.findOne("toto@gmail.com");

    }

}

 **/