package com.example.demo.mappers;

import com.example.demo.dto.aluno.AlunoResponseDTO;
import com.example.demo.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    @Mapping(target = "usuario", source = "entity.usuario")
    @Mapping(target = "usuario.cpf", source = "entity.usuario.cpfMascarado")
    AlunoResponseDTO fromEntityToDTO(Aluno entity);
}
