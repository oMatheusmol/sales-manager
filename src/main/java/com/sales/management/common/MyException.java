package com.sales.management.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sales.management.common.Erro.Erro;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyException extends ResponseEntityExceptionHandler {

    private static final Object CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final Object CONSTANT_VALIDATION_LENGTH = "Length";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Erro> errors = gerarLIstadeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
            WebRequest request) {
        String msgUsuario = "Recurso nao existe.";
        String msgDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> gerarLIstadeErros(BindingResult bindingResult) {
        List<Erro> errors = new ArrayList<Erro>();
        bindingResult.getFieldErrors().forEach(e -> {
            String msgUsuario = tratarMessage(e);
            String msgDeselvolvedor = e.toString();
            errors.add(new Erro(msgUsuario, msgDeselvolvedor));
        });
        return errors;
    }

    private String tratarMessage(FieldError e) {
        if (e.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)) {
            return "O campo " + e.getField() + " não pode ser vazio";
        }
        if (e.getCode().equals(CONSTANT_VALIDATION_LENGTH)) {
            return e.getDefaultMessage().concat(
                    String.format(" deve ter enter %s e %s caracteres.", e.getArguments()[2], e.getArguments()[1]));
        }
        return e.toString();
    }
}
