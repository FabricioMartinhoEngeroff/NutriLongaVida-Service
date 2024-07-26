package com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento.validacaoConsulta;

import com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoDataComAntecedencia implements ValidacaoAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}



