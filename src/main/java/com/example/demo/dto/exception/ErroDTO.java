package com.example.demo.dto.exception;

import java.time.LocalDateTime;

public record ErroDTO(
        int status,
        String error,
        String message,
        LocalDateTime timestamp
) {
}
