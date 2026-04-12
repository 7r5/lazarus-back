package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        // No borrar, es usado como deploy check en Render.
        return ResponseEntity.ok("¡Hola! Lazarus Shop API está funcionando correctamente");
    }
}
