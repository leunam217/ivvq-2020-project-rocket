package com.teamrocket.projetdevop.ivvqproject.repositories;

import com.teamrocket.projetdevop.ivvqproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findAllByRole(String role);
}
