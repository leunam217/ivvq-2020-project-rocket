package com.teamrocket.projetdevop.ivvqproject.vo.response;

import lombok.Data;


@Data
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String account;
    private String name;
    private String role;

    public JwtResponse(String token, String account, String name, String role) {
        this.account = account;
        this.name = name;
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
