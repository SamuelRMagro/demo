package com.example.demo.mappers;

import com.example.demo.dto.curso.CursoCreateDTO;
import com.example.demo.dto.curso.CursoResponseDTO;
import com.example.demo.dto.curso.CursoUpdateDTO;
import com.example.demo.model.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {
    CursoResponseDTO fromEntityToDTO(Curso entity);
    Curso fromCreateDTOtoEntity(CursoCreateDTO dto);
    void fromUpdateDTOtoEntity(@MappingTarget Curso curso, CursoUpdateDTO updateDTO);
}
