package edu.aviral;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	// 1. generate token
	public String generateJwtToken(String jwtId, String subject, String secretKey) {
		return Jwts.builder()
					.setId(jwtId)
					.setSubject(subject) //client name
					.setIssuer("Aviral")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(
							new Date(System.currentTimeMillis()+ TimeUnit.MINUTES.toMillis(10))) //10 minutes
					.signWith(SignatureAlgorithm.HS256, 
								Base64.getEncoder().encode(secretKey.getBytes()))
					.compact();
	}
	
	// 2. get claims
	public Claims getClaims(String secretKey, String token) {
	
		return Jwts.parser()
					.setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
					.parseClaimsJws(token)
					.getBody();
	}
	
	
	public String getSubject(String key, String token) {
		return getClaims(key, token).getSubject();
	}
	
	public boolean isValidToken(String key, String token) {
		//expDate > current then valid
		boolean after = getClaims(key, token).getExpiration().after(new Date());
		return after;
	}
}
