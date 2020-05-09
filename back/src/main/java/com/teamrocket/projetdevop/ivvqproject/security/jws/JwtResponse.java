package com.teamrocket.projetdevop.ivvqproject.security.jws;

import lombok.Data;

@Data
public class JwtResponse {

    private String token;
    private String type = "Rocket";
    private String account;
    private String name;
    private String role;

    public JwtResponse(String token, String account, String name, String role) {
        this.account = account;
        this.name = name;
        this.token = token;
        this.role = role;
    }
}
