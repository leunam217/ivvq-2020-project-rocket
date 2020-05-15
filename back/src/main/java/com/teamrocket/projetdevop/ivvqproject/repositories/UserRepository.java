package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Collection<User> findAllByRole(String role);
}
