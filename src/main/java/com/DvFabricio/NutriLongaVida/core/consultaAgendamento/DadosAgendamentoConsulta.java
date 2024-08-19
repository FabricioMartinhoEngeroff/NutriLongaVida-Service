package com.DvFabricio.NutriLongaVida.core.consultaAgendamento;

import com.DvFabricio.NutriLongaVida.core.nutricionista.domain.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        @NotNull
        Long idNutricionista,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
