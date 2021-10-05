package edu.aviral;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtDemo {
	
	public static void main(String args[]) {
											// only provider should have access to the key
		String secretKey = "secretKeydemo";  // store key in DB or file securely
		
		JwtUtil jwtUtil = new JwtUtil();
		//token is given back to the consumer application
		String token = jwtUtil.generateJwtToken("58AA#", "mysubject", secretKey);
		System.out.println(token);
		
		Claims c = jwtUtil.getClaims(secretKey, token);
		System.out.println(c);
		System.out.println(c.getIssuedAt());
		System.out.println(c.getExpiration());
		
		
//		String secretKey = "123@52#";
//		byte[] secretBytes = secretKey.getBytes();
//		byte[] base64EncodedSecretKey = Base64.getEncoder().encode(secretBytes);
//		
//		//token generation
//		String token = Jwts.builder()
//			.setId("demoId123")
//			.setSubject("demoSubject")
//			.setIssuer("Aviral")
//			.setIssuedAt(new Date(System.currentTimeMillis()))
//			.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(10)))  //10 minutes
////			.signWith(SignatureAlgorithm.HS256, secretKey)
//			.signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
//			.compact();
//		
//		System.out.println(token);
//		
//		
//		// ----------- Reading/Parsing the token -----------------//
//		//reading token/parsing token/ claiming jwt token
//		Claims claims = Jwts.parser()
//			.setSigningKey(base64EncodedSecretKey)
//			.parseClaimsJws(token)
//			.getBody();
//		
//		System.out.print(claims);
	}
}

