package com.example.demo.dto.professor;

import java.time.LocalDate;

public record ProfessorResponseDTO(
        Long id,
        Long usuarioId,
        String nome,
        String email,
        String cpf,
        Boolean ativo,
        LocalDate dataAdmissao,
        LocalDate dataCriacao,
        LocalDate dataAtualizacao){
}