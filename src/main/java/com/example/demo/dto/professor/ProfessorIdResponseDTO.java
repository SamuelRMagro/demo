package com.example.demo.dto.professor;

import com.example.demo.dto.titulacao.TitulacaoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record ProfessorIdResponseDTO(
        Long id,
        Long usuarioId,
        String nome,
        String email,
        String cpf,
        Boolean ativo,
        LocalDate dataAdmissao,
        LocalDate dataCriacao,
        LocalDate dataAtualizacao,
        List<TitulacaoResponseDTO> titulacoes
) {
}
