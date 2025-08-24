package com.danilodev.ecommerce.backend.infraestructure.jwt;

import com.danilodev.ecommerce.backend.infraestructure.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static com.danilodev.ecommerce.backend.infraestructure.jwt.Constants.*;

public class JWTValidate {

    //valida que el token venga en peticion
    public static boolean tokenExists(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if(header == null || !header.startsWith(TOKEN_BEARER_PREFIX)){
            return false;
        }
        return true;
    }

    //valida que el token sea correcto
    public static Claims JWTValid(HttpServletRequest request){
        String JwtToken = request.getHeader(HEADER_AUTHORIZATION).replace(TOKEN_BEARER_PREFIX,"");
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignedKey(SUPER_SECRET_KEY))
                .build()
                .parseClaimsJws(JwtToken)
                .getBody();
    }
//autenticar usuario en flujo de spring
    public static void setAuthentication(Claims claims, CustomUserDetailService customUserDetailService){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
