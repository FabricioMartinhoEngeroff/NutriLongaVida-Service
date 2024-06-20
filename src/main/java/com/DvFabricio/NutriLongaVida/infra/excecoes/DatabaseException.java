package com.DvFabricio.NutriLongaVida.infra.excecoes;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
