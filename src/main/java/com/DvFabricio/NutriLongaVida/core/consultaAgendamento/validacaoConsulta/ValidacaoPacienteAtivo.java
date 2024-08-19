package com.DvFabricio.NutriLongaVida.core.consultaAgendamento.validacaoConsulta;

import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.DadosAgendamentoConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import com.DvFabricio.NutriLongaVida.core.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteAtivo implements ValidacaoAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idPaciente() == null) {
            return;
        }

        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
