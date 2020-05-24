package com.teamrocket.projetdevop.ivvqproject.util;

import lombok.Getter;

@Getter
public enum UserRoleEnum {

     ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_SELLER("ROLE_SELLER");


    private String role;

    UserRoleEnum( String role) {
        this.role = role;
    }
}
