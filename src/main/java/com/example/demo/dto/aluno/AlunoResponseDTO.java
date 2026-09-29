package com.example.demo.dto.aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        LocalDate dataNascimento,
        Boolean isAtivo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
