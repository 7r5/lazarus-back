package com.example.controller;

import com.example.model.Comment;
import com.example.model.CommentDTO;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comentarios", description = "Endpoints para gestionar comentarios de productos")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    @GetMapping
    @Operation(summary = "Obtener todos los comentarios", description = "Devuelve una lista de todos los comentarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida exitosamente")
    })
    public ResponseEntity<List<Comment>> getAll() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo comentario", description = "Crea un nuevo comentario usando CommentDTO")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Comentario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos del comentario inválidos")
    })
    public ResponseEntity<Comment> create(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveCommentFromDTO(commentDTO));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Obtener comentarios de un producto", description = "Devuelve los comentarios de un producto específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Comentarios obtenidos exitosamente")
    })
    public ResponseEntity<List<Comment>> getCommentsByProduct(@Parameter(description = "ID del producto") @PathVariable Long productId) {
        return ResponseEntity.ok(commentService.getCommentsByProductAndType(productId, "Product"));
    }
}