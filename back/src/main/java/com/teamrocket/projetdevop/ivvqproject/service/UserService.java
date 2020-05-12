package com.teamrocket.projetdevop.ivvqproject.service;


import com.teamrocket.projetdevop.ivvqproject.domain.User;

public interface UserService {
    User findOne(String email);

    User save(User user);

    User update(User user);
}
