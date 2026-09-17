package com.example.demo.controller;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
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
}
