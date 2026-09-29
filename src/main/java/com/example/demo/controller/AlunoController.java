package com.example.demo.controller;

import com.example.demo.dto.aluno.AlunoFilterRequest;
import com.example.demo.dto.aluno.AlunoResponseDTO;
import com.example.demo.service.aluno.AlunoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AlunoResponseDTO>> listarAlunos(
            @PageableDefault(
                    sort = "nome",
                    direction = Sort.Direction.ASC
            ) Pageable pageable, @Valid @ParameterObject AlunoFilterRequest request){
        Page<AlunoResponseDTO> alunos = service.listagemAlunos(pageable, request);
        return ResponseEntity.ok(alunos);
    }
}
