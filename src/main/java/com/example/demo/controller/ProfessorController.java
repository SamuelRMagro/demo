package com.example.demo.controller;

import com.example.demo.dto.professor.ProfessorFilterRequest;
import com.example.demo.dto.professor.ProfessorResponseDTO;
import com.example.demo.service.professor.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
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
@RequestMapping("/professores")
@Validated
@Tag(name = "Recurso de professores", description = "Endpoints para o gerenciamento do recurso professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @Operation(
            summary = "Consulta de professores",
            description = "Busca professores paginados com filtros"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProfessorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    @GetMapping
    @PageableAsQueryParam
    public ResponseEntity<Page<ProfessorResponseDTO>> listarProfessores(
            @ParameterObject
            @PageableDefault(
                    sort = "usuario.nome",
                    direction = Sort.Direction.ASC
            )Pageable pageable, @Valid @ParameterObject ProfessorFilterRequest request) {
        Page<ProfessorResponseDTO> professores = service.listagemProfessores(pageable, request);
        return ResponseEntity.ok(professores);
    }
}