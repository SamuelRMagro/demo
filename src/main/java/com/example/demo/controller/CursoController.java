package com.example.demo.controller;

import com.example.demo.dto.curso.CursoFilterRequest;
import com.example.demo.dto.curso.CursoResponseDTO;
import com.example.demo.service.curso.CursoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
@Validated
public class CursoController {

    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CursoResponseDTO>> listarCursos(
            @PageableDefault(
                    sort = "nome",
                    direction = Sort.Direction.ASC
            ) Pageable pageable, @Valid @ParameterObject CursoFilterRequest request){
        Page<CursoResponseDTO> cursos = service.listagemCursos(pageable, request);
        return ResponseEntity.ok(cursos);
    }
}
