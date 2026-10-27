package com.zenkai.pe.UniversidadPOO.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/v1")
@RequiredArgsConstructor
public class HomeController {
    
    @GetMapping("/home")
    public String home(){
        return "Private home";
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ESTUDIANTE')")
    public String estudiante(){
        return "estudiante";
    }
}
