package com.example.demo.dto.curso;

import com.example.demo.customValidations.anotacoesCustomizadas.DataDentroIntervalo;

import java.time.LocalDate;

public record CursoFilterRequest(
        String nome,
        Integer cargaHoraria,
        Integer duracaoSemestre,
        Boolean isAtivo,

        @DataDentroIntervalo
        LocalDate dataCadastroInicio,

        @DataDentroIntervalo
        LocalDate dataCadastroFim
) {
}
