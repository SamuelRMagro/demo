package com.example.demo.dto.usuario;

import com.example.demo.customValidations.anotacoesCustomizadas.DataDentroIntervalo;

import java.time.LocalDate;

public record UsuarioRequest(
        String nome,
        String email,
        Boolean isAtivo,

        @DataDentroIntervalo
        LocalDate dataCadastroInicio,

        @DataDentroIntervalo
        LocalDate dataCadastroFim
) {
}
