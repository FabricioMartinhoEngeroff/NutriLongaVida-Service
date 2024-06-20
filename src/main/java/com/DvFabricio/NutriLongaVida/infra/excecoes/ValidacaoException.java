package com.DvFabricio.NutriLongaVida.infra.excecoes;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String message) {
        super(message);
    }
}