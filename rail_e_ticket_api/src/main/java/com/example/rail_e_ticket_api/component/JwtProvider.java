package com.example.rail_e_ticket_api.component;

import com.example.rail_e_ticket_api.entity.Owner;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtProvider {

    private final String secretKey="arra";

    public String generate(Owner owner){
        long expire=3600_000;
        return Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .setSubject(owner.getUsername())
                .addClaims(Map.of("permissions",owner.getPermissions()))
                .signWith(SignatureAlgorithm.HS512,secretKey)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature -> Message: {} ");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token -> Message: {}");
        } catch (ExpiredJwtException e) {
            System.out.println("Expired JWT token -> Message: {}");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT token -> Message: {}");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty -> Message: {}");
        }
        return false;
    }

    public Owner getOwnerByToken(String token){
        Claims body = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return new Owner(body.getSubject(),body.get("permissions",Integer.class));
    }

}
