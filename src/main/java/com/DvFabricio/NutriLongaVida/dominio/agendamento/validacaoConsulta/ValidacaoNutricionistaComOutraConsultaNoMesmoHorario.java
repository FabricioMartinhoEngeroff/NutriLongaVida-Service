package com.DvFabricio.NutriLongaVida.dominio.agendamento.validacaoConsulta;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.ConsultaRepository;
import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoNutricionistaComOutraConsultaNoMesmoHorario implements ValidacaoAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var nutricionistaPossuiOutraConsultaNoMesmoHorario = repository.existsByNutricionistaIdAndDataAndMotivoCancelamentoIsNull(dados.idNutricionista(), dados.data());
        if (nutricionistaPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Nutricionista já possui outra consulta agendada nesse mesmo horário");
        }
    }
}