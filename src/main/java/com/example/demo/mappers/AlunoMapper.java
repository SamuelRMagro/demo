package com.example.demo.mappers;

import com.example.demo.dto.aluno.AlunoResponseDTO;
import com.example.demo.model.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {
    @Mapping(target = "cpf", expression = "java(getCpfMascarado(entity))")
    @Mapping(target = "nome", source = "entity.usuario.nome")
    @Mapping(target = "email", source = "entity.usuario.email")
    @Mapping(target = "isAtivo", source = "entity.usuario.isAtivo")
    @Mapping(target = "dataCriacao", source = "entity.usuario.dataCadastro")
    @Mapping(target = "dataAtualizacao", source = "entity.usuario.dataAtualizacao")
    AlunoResponseDTO fromEntityToDTO(Aluno entity);

    default String getCpfMascarado(Aluno aluno) {
        return aluno.getUsuario().cpfMascarado();
    }
}
