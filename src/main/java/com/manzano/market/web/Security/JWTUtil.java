package com.manzano.market.web.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static final String KEY = "4le3x";

    public String generarToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /*Ya obtenidos los claims, se obtiene el usuario para el que esta creado el token*/
    public String extractUserName(String token){
        return getClaims(token).getSubject();
    }

    /*Metodo para establecer la validar que el token a introducir sea correcto*/
    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUserName(token)) && !tokenExpiro(token);
    }

    /*Validamos si el token aun esta vivo o si ya expiro*/
    public boolean tokenExpiro(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    /*Los Claims son aquel objeto que mapea el JWT*/
    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

}
