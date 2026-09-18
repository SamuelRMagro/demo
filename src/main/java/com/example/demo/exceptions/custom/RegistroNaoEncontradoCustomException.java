package com.example.demo.exceptions.custom;

public class RegistroNaoEncontradoCustomException extends RuntimeException {
    public RegistroNaoEncontradoCustomException(String message) {
        super(message);
    }
}
