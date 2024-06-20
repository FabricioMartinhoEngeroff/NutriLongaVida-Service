package com.DvFabricio.NutriLongaVida.dominio.agendamento.cancelamento;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.ConsultaRepository;
import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosCancelarConsulta;
import com.DvFabricio.NutriLongaVida.infra.excecoes.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidacaoHorarioAntecedenciaCancelamento")
public class ValidacaoHorarioAntecedencia implements ValidacaoCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosCancelarConsulta dados) {
        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}