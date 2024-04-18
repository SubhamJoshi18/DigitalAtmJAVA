package com.subham.ATM.service;

import com.subham.ATM.model.AccountHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;


@Service
public class JwtService {




    private static final String SECRET_KEY = "25574a51d1210efaeb9afe200455bfc4a39273fb6e5983386431d5c21564a996";
    private static final long EXPIRATION_TIME = 864_000_000;

    public String generateJWTToken(AccountHolder accountHolder) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(accountHolder.getName())
                .claim("authorities",populateAuthorities(accountHolder.getAuthorities()))
                .claim("phoneNumber", accountHolder.getPhoneNumber())
                .claim("role", accountHolder.getRole())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getTokenKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public SecretKey getTokenKey(){
        byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keys);
    }



    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities){
        Set<String> authoritiesSet = new HashSet<>();
        for(GrantedAuthority authority:authorities){
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }


    public String extraUsername(String token){
        return  extractClaims(token, Claims::getSubject);

    }

    public <T> T extractClaims(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }



    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getTokenKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extraUsername(token);
        return username.equals(userDetails.getUsername());
    }

}
