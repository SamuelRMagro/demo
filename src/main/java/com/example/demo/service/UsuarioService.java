package com.example.demo.service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    private Page<UsuarioDTO> converteDadosPaginados(Page<Usuario> usuarioPage) {
        return usuarioPage.map(u -> new UsuarioDTO(
                u.getId(),
                u.getNome(),
                u.getIsAtivo(),
                u.getIdade(),
                u.getEmail(),
                u.getLogin(),
                u.cpfMascarado(),
                u.getDataCadastro(),
                u.getDataAtualizacao()
        ));
    }

    private List<UsuarioDTO> converteDadosEmLista(List<Usuario> usuariosList) {
        return usuariosList.stream().map(u -> new UsuarioDTO(
                u.getId(),
                u.getNome(),
                u.getIsAtivo(),
                u.getIdade(),
                u.getEmail(),
                u.getLogin(),
                u.cpfMascarado(),
                u.getDataCadastro(),
                u.getDataAtualizacao()
        )).toList();
    }

    public Page<UsuarioDTO> listagemUsuarios(Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);

        return converteDadosPaginados(usuarioPage);
    }
}
