package com.example.demo.dto.usuario;

import java.time.LocalDateTime;

public record UsuarioDTO (
        Long id,
        String nome,
        Boolean isAtivo,
        Integer idade,
        String email,
        String login,
        String cpf,
        LocalDateTime dataCadastro,
        LocalDateTime dataAtualizacao) {
}
