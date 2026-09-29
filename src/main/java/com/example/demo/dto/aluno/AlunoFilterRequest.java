package com.example.demo.dto.aluno;

public record AlunoFilterRequest(
        String nome,
        String email,
        String cpf,
        Boolean isAtivo
) {
}
