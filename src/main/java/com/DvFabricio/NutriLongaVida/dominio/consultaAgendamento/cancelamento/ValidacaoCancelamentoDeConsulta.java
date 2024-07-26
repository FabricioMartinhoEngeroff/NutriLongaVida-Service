package com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento.cancelamento;

import com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento.DadosCancelarConsulta;

public interface ValidacaoCancelamentoDeConsulta {
    void validar(DadosCancelarConsulta dados);
}
