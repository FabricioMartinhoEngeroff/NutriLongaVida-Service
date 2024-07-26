package com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento;

import com.DvFabricio.NutriLongaVida.dominio.entities.nutricionista.Especialidade;
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
