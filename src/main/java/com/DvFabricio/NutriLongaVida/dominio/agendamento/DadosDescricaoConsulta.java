package com.DvFabricio.NutriLongaVida.dominio.agendamento;

import java.time.LocalDateTime;

public record DadosDescricaoConsulta(Long id, Long idNutricionista, Long idPaciente, LocalDateTime data) {

    public DadosDescricaoConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getNutricionista().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
