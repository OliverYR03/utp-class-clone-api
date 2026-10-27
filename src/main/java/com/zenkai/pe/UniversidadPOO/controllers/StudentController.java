package com.zenkai.pe.UniversidadPOO.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/home")
    public String home(){
        return "Public home";
    }
}
