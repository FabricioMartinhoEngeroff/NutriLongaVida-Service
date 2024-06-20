package com.DvFabricio.NutriLongaVida.dominio.agendamento.validacaoConsulta;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import com.DvFabricio.NutriLongaVida.dominio.nutricionista.NutricionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoNutricionistaAtivo implements ValidacaoAgendamentoDeConsulta {

    @Autowired
    private NutricionistaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idNutricionista() == null) {
            return;
        }

        var nutricionistaEstaAtivo = repository.findAtivoById(dados.idNutricionista());
        if (!nutricionistaEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com nutricionista excluído!");
        }
    }
}