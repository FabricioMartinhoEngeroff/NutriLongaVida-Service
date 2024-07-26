package com.DvFabricio.NutriLongaVida.dominio.consultaAgendamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByNutricionistaIdAndDataAndMotivoCancelamentoIsNull(Long idNutricionista, LocalDateTime data);

    Page<Consulta> findByPacienteId(Long idPaciente, Pageable pageable);

    @Query("SELECT c FROM Consulta c WHERE c.data = :data")
    Page<Consulta> findByDataConsulta(@Param("data") LocalDateTime data, Pageable pageable);
}