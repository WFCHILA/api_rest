package com.estoqueApi.estoque_api.exception; // PACOTE CORRETO

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Importante: Referencie as classes de exceção que você criou aqui
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNaoEncontradoException(RecursoNaoEncontradoException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<String> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Ocorreu um erro interno no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}