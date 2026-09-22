package com.example.demo.dto.usuario;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioCreateDTO(
        @NotNull(message = "O campo nome não pode ser nulo.")
        @Size(min = 5, max = 250, message = "O campo 'nome' deve ter entre {min} e {max} caracteres.")
        String nome,

        @NotNull(message = "O campo idade não pode ser nulo.")
        @Min(value = 5, message = "A idade minima é de {value} anos.")
        @Max(value = 130, message = "A idade máxima é de {value} anos.")
        Integer idade,

        @NotNull(message = "O campo login não pode ser nulo.")
        @Size(min = 5, max = 250, message = "O campo 'login' deve ter entre {min} e {max} caracteres.")
        String login,

        @NotNull(message = "O campo email não pode ser nulo.")
        @Email(message = "E-mail com formato inválido.")
        @Size(min = 5, max = 250, message = "O campo 'email' deve ter entre {min} e {max} caracteres.")
        String email,

        @NotNull(message = "O campo cpf não pode ser nulo.")
        @CPF(message = "O CPF deve ter exatamente 11 dígitos.")
        String cpf,

        @NotNull(message = "O campo senha não pode ser nulo.")
        @Size(min = 5, max = 50, message = "O campo 'senha' deve ter entre {min} e {max} caracteres.")
        String senha,
        Boolean isAtivo) {
}
