package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
