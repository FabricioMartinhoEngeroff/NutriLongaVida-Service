package com.DvFabricio.NutriLongaVida.infra.excecoes;

public class ResourceUnauthorizedException extends RuntimeException {
    public ResourceUnauthorizedException(String message) {
        super(message);
    }
}
