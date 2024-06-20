package com.DvFabricio.NutriLongaVida.dominio.agendamento.validacaoConsulta;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.ConsultaRepository;
import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalTime;

public class ValidacaoPacienteSemNenhumaConsultaNoDia implements ValidacaoAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().with(LocalTime.of(10, 0));
        var ultimoHorario = dados.data().with(LocalTime.of(18, 0));
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
