package com.teamrocket.projetdevop.ivvqproject.requestresponse;

import lombok.Data;

import javax.validation.constraints.NotBlank;



@Data
public class AuthentificationForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthentificationForm(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
}
