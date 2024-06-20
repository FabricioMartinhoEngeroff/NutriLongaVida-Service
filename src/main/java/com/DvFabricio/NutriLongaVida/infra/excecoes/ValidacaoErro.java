package com.DvFabricio.NutriLongaVida.infra.excecoes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ValidacaoErro extends ErroPadrao {
    private static final long serialVersionUID = 1L;

    private List<MensagemDoCampo> errors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        errors.add(new MensagemDoCampo(fieldName, message));
    }
}
