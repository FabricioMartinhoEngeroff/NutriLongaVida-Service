package com.DvFabricio.NutriLongaVida.core.consultaAgendamento.service;

import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.*;
import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.repository.ConsultaRepository;
import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.validacaoConsulta.cancelamento.ValidacaoCancelamentoDeConsulta;
import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.validacaoConsulta.ValidacaoAgendamentoDeConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Nutricionista;
import com.DvFabricio.NutriLongaVida.core.nutricionista.repository.NutricionistaRepository;
import com.DvFabricio.NutriLongaVida.core.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidacaoAgendamentoDeConsulta> validadores;

    @Autowired
    private List<ValidacaoCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDescricaoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idNutricionista() != null && !nutricionistaRepository.existsById(dados.idNutricionista())) {
            throw new ValidacaoException("Id do nutricionista informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var nutricionista = escolherNutricionista(dados);
        if (nutricionista == null) {
            throw new ValidacaoException("Não existe nutricionista disponível nessa data!");
        }

        var consulta = new Consulta(null, nutricionista, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDescricaoConsulta(consulta);
    }

    public void cancelar(DadosCancelarConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }

    private Nutricionista escolherNutricionista(DadosAgendamentoConsulta dados) {
        if (dados.idNutricionista() != null) {
            return nutricionistaRepository.getReferenceById(dados.idNutricionista());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando nutricionista não for escolhido!");
        }

        return nutricionistaRepository.escolherNutricionistaAleatorioDisponivelNaData(dados.especialidade(), LocalDate.from(dados.data()));
    }
}