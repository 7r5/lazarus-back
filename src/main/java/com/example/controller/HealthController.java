package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
@Tag(name = "Health", description = "Endpoints de verificación de estado de la API")
public class HealthController {

    @GetMapping("/hello")
    @Operation(summary = "Verificar estado de la API", description = "Endpoint de health check para verificar que la API está funcionando")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "API funcionando correctamente")
    })
    public ResponseEntity<String> hello() {
        // No borrar, es usado como deploy check en Render.
        return ResponseEntity.ok("¡Hola! Lazarus Shop API está funcionando correctamente");
    }
}
