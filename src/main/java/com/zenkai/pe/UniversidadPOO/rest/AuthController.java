package com.zenkai.pe.UniversidadPOO.rest;

import com.zenkai.pe.UniversidadPOO.dto.LoginDTO;
import com.zenkai.pe.UniversidadPOO.security.JWTAuthResponseDTO;
import com.zenkai.pe.UniversidadPOO.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        System.out.println("Entró al login");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getCodigo(),
                        loginDTO.getPassword()
                )
        );
        

        System.out.println("Autenticación correcta");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generarToken(authentication);
       
        
        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }
}
