package com.example.configure;

import java.io.Serializable;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.example.service.UserDetailsService;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenProvider {


	private String makeJwtToken(String userId, String nickname) {
		Date now = new Date();
		
		return Jwts.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setIssuer("waterfulTest")
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis()))
				.claim("userId", userId)
				.claim("nickname", nickname)
				.signWith(SignatureAlgorithm.HS256, "secret")
				.compact();
 }
	

}
