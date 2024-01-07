package com.example.bds.config;

import com.example.bds.constant.SystemConstant;
import com.example.bds.exception.CustomRuntimeException;
import com.example.bds.user.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtTokenProvider {
    private static final String JWT_SECRET = SystemConstant.JWT_SECRET;
    private static final long JWT_EXPIRATION = SystemConstant.JWT_EXPIRATION;

    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder()
                .setSubject(userDetails.getUser().getUserName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validateToken(String authToken) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {

        try{
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(authToken);
            return true;
        }catch (ExpiredJwtException ex){
            throw ex;
        }catch (UnsupportedJwtException ex){
            throw ex;
        }catch (MalformedJwtException ex){
            throw ex;
        }catch (SignatureException ex){
            throw ex;
        }catch (IllegalArgumentException ex){
            throw ex;
        }
    }

    public String getUserNameFromJWT(String jwt) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims.getSubject();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
