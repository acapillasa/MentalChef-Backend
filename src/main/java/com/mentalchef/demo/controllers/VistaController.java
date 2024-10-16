package com.mentalchef.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class VistaController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public String mostrarPagina() {
        String url = "http://127.0.0.1:5500/index.html";
        
        String contenido = restTemplate.getForObject(url, String.class);
    
        return contenido;
    }
    
}
