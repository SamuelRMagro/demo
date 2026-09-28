package com.example.demo.mappers;

import com.example.demo.dto.curso.CursoResponseDTO;
import com.example.demo.model.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoResponseDTO fromEntityToDTO(Curso entity);
}
