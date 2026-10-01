package com.example.demo.dto.aluno;

import io.swagger.v3.oas.annotations.media.Schema;

public record AlunoFilterRequest(

        @Schema(description = "Nome do aluno (aceita parcial)", defaultValue = "Ana", example = "João")
        String nome,

        @Schema(description = "Email do aluno (aceita parcial)", defaultValue = "ana.souza@email.com", example = "joao.pereira@email.com")
        String email,

        @Schema(description = "cpf do aluno (aceita parcial)", defaultValue = "906", example = "987")
        String cpf,

        @Schema(description = "Status do aluno (V/F)", defaultValue = "true", example = "false")
        Boolean isAtivo
) {
}
