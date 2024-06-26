package com.DvFabricio.NutriLongaVida.dominio.agendamento;

import jakarta.validation.constraints.NotNull;

public record DadosCancelarConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}
