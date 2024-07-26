package com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelarConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}
