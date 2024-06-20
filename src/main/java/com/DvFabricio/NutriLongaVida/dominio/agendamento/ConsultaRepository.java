package com.DvFabricio.NutriLongaVida.dominio.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByNutricionistaIdAndDataAndMotivoCancelamentoIsNull(Long idNutricionista, LocalDateTime data);


}
