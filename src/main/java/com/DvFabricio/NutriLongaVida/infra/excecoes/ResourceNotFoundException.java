package com.DvFabricio.NutriLongaVida.infra.excecoes;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}


