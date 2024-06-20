package com.DvFabricio.NutriLongaVida.dominio.agendamento.cancelamento;

import com.DvFabricio.NutriLongaVida.dominio.agendamento.DadosCancelarConsulta;

public interface ValidacaoCancelamentoDeConsulta {
    void validar(DadosCancelarConsulta dados);
}
