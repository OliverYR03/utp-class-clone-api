package com.zenkai.pe.UniversidadPOO.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationInMs;

    private SecretKey obtenerClave() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generarToken(Authentication authentication) {
        Date fechaActual = new Date();
        Date fechaExpiracion
                = new Date(fechaActual.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(fechaActual)
                .expiration(fechaExpiracion)
                .signWith(obtenerClave(), Jwts.SIG.HS512)
                .compact();
    }

    public String obtenerCodigoDelJWT(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(obtenerClave())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(obtenerClave())
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (ExpiredJwtException ex) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Token JWT caducado", ex);
        } catch (io.jsonwebtoken.JwtException ex) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Token JWT no válido", ex);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "El token JWT está vacío", ex);
        }
    }
}
