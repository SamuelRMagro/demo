package com.example.demo.controller;

import com.example.demo.dto.usuario.UsuarioCreateDTO;
import com.example.demo.dto.usuario.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> listarUsuarios(
            @PageableDefault(
                    sort = "nome",
                    direction = Sort.Direction.ASC
            )Pageable pageable){
        Page<UsuarioDTO> usuarios = service.listagemUsuarios(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscaUsuarioId(
            @PathVariable Long id
    ) {
        UsuarioDTO dto = service.usuarioId(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(
            @Valid @RequestBody UsuarioCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarUsuario(createDTO));
    }
}
