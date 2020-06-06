package com.teamrocket.projetdevop.ivvqproject.security.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JsonWebTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenProvider.class);

	@Value("${jwtRocket}")
	private String jwtRocket;
	@Value("${jwtExpiration}")
	private int jwtExpiration;

	public String generate(Authentication authentication) {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration * 1000))
				.signWith(SignatureAlgorithm.HS512, jwtRocket).compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtRocket).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			logger.error("JWT Authentication Failed");
		}
		return false;
	}

	public String getUserAccount(String token) {
		return Jwts.parser().setSigningKey(jwtRocket).parseClaimsJws(token).getBody().getSubject();
	}
}
