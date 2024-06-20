package com.DvFabricio.NutriLongaVida.dominio.agendamento.validacaoConsulta;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioDisponivelNutricionista implements ValidacaoAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorarioAtendimentoNutri = dataConsulta.getHour() < 10;
        var depoisDoHorarioAtendimentoNutri = dataConsulta.getHour() >= 18;

        if (domingo || antesDoHorarioAtendimentoNutri || depoisDoHorarioAtendimentoNutri) {
            throw new ValidacaoException("Consulta fora do horário de atendimento da nutricionista!");
        }
    }
}
