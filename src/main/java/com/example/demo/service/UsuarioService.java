package com.example.demo.service;

import com.example.demo.dto.exception.ErroCampo;
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.dto.usuario.UsuarioCreateDTO;
import com.example.demo.exceptions.custom.CadastroInvalidoCustomException;
import com.example.demo.exceptions.custom.RegistroNaoEncontradoCustomException;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private UsuarioDTO converteUsuarioOptional(Optional<Usuario> optionalUsuario, Long id){

        if (optionalUsuario.isEmpty()){
            throw new RegistroNaoEncontradoCustomException(String.format("Usuário de id=%d não encontrado.", id));
        }

        Usuario user = optionalUsuario.get();

        return new UsuarioDTO(user.getId(), user.getNome(), user.getIsAtivo(), user.getIdade(),
                user.getEmail(), user.getLogin(), user.cpfMascarado(), user.getDataCadastro(), user.getDataAtualizacao());
    }

    private UsuarioDTO converteUsuario(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getIsAtivo(), usuario.getIdade(),
                usuario.getEmail(), usuario.getLogin(), usuario.cpfMascarado(), usuario.getDataCadastro(), usuario.getDataAtualizacao());
    }

    private String limpaCpf(String cpf){
        if ( cpf == null) return null;

        String limpo = cpf.replaceAll("\\D", "");

        if (limpo.length() != 11){
            return null;
        }

        return limpo;
    }

    public Page<UsuarioDTO> listagemUsuarios(Pageable pageable) {
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);

        return converteDadosPaginados(usuarioPage);
    }

    public UsuarioDTO usuarioId(Long id){
        return converteUsuarioOptional(usuarioRepository.findById(id), id);
    }

    public void inativar(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()){
            throw new RegistroNaoEncontradoCustomException(String.format("Usuário de id=%d não encontrado.", id));
        } else if(usuario.get().inativo()) {
            return;
        } else {
            Usuario user = usuario.get();
            user.setIsAtivo(false);
            user.setDataAtualizacao(LocalDateTime.now());
            user.setDataInativacao(LocalDateTime.now());
            usuarioRepository.save(user);
        }
    }

    public UsuarioDTO cadastrarUsuario(UsuarioCreateDTO dto) {
         Usuario usuario = new Usuario(dto.isAtivo(), dto.email(), dto.login(), dto.idade(), limpaCpf(dto.cpf()), dto.nome());

         List<ErroCampo> erros = new ArrayList<>();

         if (usuarioRepository.existsByEmail(usuario.getEmail())){
             erros.add(new ErroCampo("email", "E-mail já cadastrado."));
         }
         if (usuarioRepository.existsByCpf(usuario.getCpf())){
             erros.add(new ErroCampo("cpf", "CPF já cadastrado."));
         }
         if (usuarioRepository.existsByLogin(usuario.getLogin())){
             erros.add(new ErroCampo("login", "Login já cadastrado."));
         }

         if (!erros.isEmpty()){
             throw new CadastroInvalidoCustomException(erros);
         }

         usuario = usuarioRepository.save(usuario);
         return converteUsuario(usuario);
    }
}
