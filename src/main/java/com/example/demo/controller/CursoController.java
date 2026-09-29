package com.example.demo.controller;

import com.example.demo.dto.curso.CursoCreateDTO;
import com.example.demo.dto.curso.CursoFilterRequest;
import com.example.demo.dto.curso.CursoResponseDTO;
import com.example.demo.dto.curso.CursoUpdateDTO;
import com.example.demo.service.curso.CursoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscaCursoId(@PathVariable Long id) {
        CursoResponseDTO dto = service.buscarOuFalharDTO(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> cadastrar(
            @Valid @RequestBody CursoCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarCurso(createDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(
            @PathVariable Long id, @Valid @RequestBody CursoUpdateDTO updateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(updateDTO, id));
    }

    @PatchMapping("{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
