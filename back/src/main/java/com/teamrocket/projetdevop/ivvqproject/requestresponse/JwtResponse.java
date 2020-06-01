package com.teamrocket.projetdevop.ivvqproject.requestresponse;

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

	public String getAccount() {
		return account;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

}
