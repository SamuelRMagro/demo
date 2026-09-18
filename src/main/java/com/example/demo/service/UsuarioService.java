package com.example.demo.service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.exceptions.custom.RegistroNaoEncontradoCustomException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private UsuarioDTO converteUsuario(Optional<Usuario> optionalUsuario, Long id){

        if (optionalUsuario.isEmpty()){
            throw new RegistroNaoEncontradoCustomException(String.format("Usuário de id=%d não encontrado.", id));
        }

        Usuario user = optionalUsuario.get();

        return new UsuarioDTO(user.getId(), user.getNome(), user.getIsAtivo(), user.getIdade(),
                user.getEmail(), user.getLogin(), user.cpfMascarado(), user.getDataCadastro(), user.getDataAtualizacao());
    }

    public Page<UsuarioDTO> listagemUsuarios(Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);

        return converteDadosPaginados(usuarioPage);
    }

    public UsuarioDTO usuarioId(Long id){
        return converteUsuario(usuarioRepository.findById(id), id);
    }
}
