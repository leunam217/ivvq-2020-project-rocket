package com.teamrocket.projetdevop.ivvqproject.repositories;


import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    void setup()
    {
        this.user = new User("bob@email.com",passwordEncoder.encode(""),"Bob","21345","Toulouse");
    }

    @Test
    public void find_by_email(){
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        assertThat(userOptional).isNotNull();
    }

    @Test
    public void findByEmail_NotPresent() {
        Optional<User> userOptional = userRepository.findById("toto@email.com");
        assertFalse(userOptional.isPresent());
    }

    @Test
    public void findByRoleNotPresent() {
        Collection<User> customerOptional = userRepository.findAllByRole("ROLE_CUSTOMER");
        assertTrue(customerOptional.isEmpty());
    }

}
