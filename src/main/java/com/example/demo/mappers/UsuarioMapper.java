package com.example.demo.mappers;

import com.example.demo.dto.usuario.UsuarioResponseDTO;
import com.example.demo.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "cpf", expression = "java(cpfMascarado(entity))")
    UsuarioResponseDTO fromEntityToDTO(Usuario entity);

    default String cpfMascarado(Usuario entity) {
        return Usuario.cpfMascarado(entity.getCpf());
    }
}
