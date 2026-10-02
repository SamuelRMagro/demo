package com.example.demo.mappers;

import com.example.demo.dto.professor.ProfessorResponseDTO;
import com.example.demo.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    @Mapping(target = "usuarioId", source = "entity.usuario.id")
    @Mapping(target = "nome", source = "entity.usuario.nome")
    @Mapping(target = "email", source = "entity.usuario.email")
    @Mapping(target = "cpf", source = "entity.usuario.cpfMascarado")
    @Mapping(target = "dataCriacao", source = "entity.usuario.dataCadastro")
    @Mapping(target = "dataAtualizacao", source = "entity.usuario.dataAtualizacao")
    ProfessorResponseDTO fromEntityToDTO(Professor entity);
}