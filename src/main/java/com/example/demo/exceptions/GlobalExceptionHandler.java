package com.example.demo.exceptions;

import com.example.demo.dto.exception.ErroDTO;
import com.example.demo.exceptions.custom.RegistroNaoEncontradoCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegistroNaoEncontradoCustomException.class)
    public ResponseEntity<ErroDTO> erroNotFound(RegistroNaoEncontradoCustomException ex){
        ErroDTO erro = new ErroDTO(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
