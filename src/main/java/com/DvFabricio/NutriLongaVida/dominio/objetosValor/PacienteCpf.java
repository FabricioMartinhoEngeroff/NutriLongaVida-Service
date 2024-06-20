package com.DvFabricio.NutriLongaVida.dominio.objetosValor;

import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@EqualsAndHashCode
@ToString
public class PacienteCpf {

    private final String valor;

    public PacienteCpf(String valor) {
        if (valor == null) {
            throw new ValidacaoException("CPF não pode ser nulo");
        }
        if (!isCpfValido(valor)) {
            throw new ValidacaoException("CPF inválido: " + valor);
        }
        this.valor = valor;
    }

    private boolean isCpfValido(String cpf) {
        try {
            cpf = cpf.replaceAll("\\D", "");
            if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
                return false;
            }
            int primeiroDigitoVerificador = calcularDigitoVerificador(cpf.substring(0, 9));
            int segundoDigitoVerificador = calcularDigitoVerificador(cpf.substring(0, 9) + primeiroDigitoVerificador);
            return cpf.equals(cpf.substring(0, 9) + primeiroDigitoVerificador + segundoDigitoVerificador);
        } catch (Exception e) {
            Logger logger = Logger.getLogger(CPF.class.getName());
            logger.log(Level.SEVERE, "Erro ao validar CPF", e);
            return false;
        }
    }

    private int calcularDigitoVerificador(String base) {
        int peso = base.length() + 1;
        int soma = 0;
        for (char digito : base.toCharArray()) {
            soma += Character.getNumericValue(digito) * peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

}
