package com.zenkai.pe.UniversidadPOO.Entity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerarPassword {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("Demo123!"));
    }
} 