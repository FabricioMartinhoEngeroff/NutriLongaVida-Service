package com.DvFabricio.NutriLongaVida.dominio.agendamento;

import com.DvFabricio.NutriLongaVida.dominio.nutricionista.Especialidade;
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
