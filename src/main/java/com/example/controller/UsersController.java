package com.example.controller;

import com.example.model.Users;
import com.example.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Endpoints para gestionar usuarios")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    })
    public ResponseEntity<List<Users>> getAll() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en la base de datos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del usuario inválidos")
    })
    public ResponseEntity<Users> create(@RequestBody Users users) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.saveUsers(users));
    }
}
