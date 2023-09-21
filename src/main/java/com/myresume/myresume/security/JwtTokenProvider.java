package com.myresume.myresume.security;

import com.myresume.myresume.entity.UersGeneralEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Value("${app.jwtSecret}")
  private String jwtSecret;

  @Value("${app.jwtExpirationInMs}")
  private long jwtExpirationInMs;

  public String generateToken(String phone, UersGeneralEntity user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    Claims claims = Jwts.claims();
    claims.put("phone", phone);
    claims.put("FirstName", user.getUserGenFirstName());
    claims.put("LastName", user.getUserGenLastName());
    claims.put("Img", user.getUserGenImg());
    claims.put("Email", user.getUsersGenEmail());

    return Jwts
      .builder()
      .setClaims(claims)
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  }
}
