package com.example.demo.dto.aluno;

import com.example.demo.dto.usuario.UsuarioResponseDTO;

import java.time.LocalDate;

public record AlunoResponseDTO(
        UsuarioResponseDTO usuario,
        Long id,
        String telefone,
        LocalDate dataNascimento
) {
}
