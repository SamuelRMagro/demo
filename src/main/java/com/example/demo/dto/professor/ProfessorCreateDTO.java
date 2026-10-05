package com.example.demo.dto.professor;

import com.example.demo.customValidations.anotacoesCustomizadas.DataDentroIntervalo;
import com.example.demo.dto.usuario.UsuarioCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ProfessorCreateDTO(

        @Valid
        UsuarioCreateDTO usuario,

        @Schema(description = "Data de admissão do professor")
        @NotNull(message = "O campo dataAdmissao não pode ser nulo.")
        @PastOrPresent(message = "A data de admissão não pode ser futura.")
        @DataDentroIntervalo
        LocalDate dataAdmissao

) {
}
