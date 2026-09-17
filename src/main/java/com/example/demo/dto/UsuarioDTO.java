package com.example.demo.dto;

import java.time.LocalDateTime;

public record UsuarioDTO (
        Long id,
        String nome,
        Boolean isAtivo,
        int idade,
        String email,
        String login,
        String cpf,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao) {
}
