package com.example.demo.dto.curso;

import java.time.LocalDateTime;

public record CursoResponseDTO(
        Long id,
        String nome,
        Integer cargaHoraria,
        Integer duracaoSemestre,
        Boolean isAtivo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
}
