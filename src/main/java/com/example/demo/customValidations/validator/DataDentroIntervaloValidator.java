package com.example.demo.customValidations.validator;

import com.example.demo.customValidations.anotacoesCustomizadas.DataDentroIntervalo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DataDentroIntervaloValidator implements ConstraintValidator<DataDentroIntervalo, LocalDate> {

    private static final LocalDate MIN = LocalDate.of(1900, 1, 1);
    private static final LocalDate MAX = LocalDate.of(2100, 12, 31);

    @Override
    public boolean isValid(LocalDate valor, ConstraintValidatorContext context) {
        if (valor == null) return true;
        return !valor.isBefore(MIN) && !valor.isAfter(MAX);
    }
}
