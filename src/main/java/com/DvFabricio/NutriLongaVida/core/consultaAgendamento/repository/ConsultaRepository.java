package com.DvFabricio.NutriLongaVida.core.consultaAgendamento.repository;

import com.DvFabricio.NutriLongaVida.core.consultaAgendamento.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    Boolean existsByNutricionistaIdAndDataAndMotivoCancelamentoIsNull(Long idNutricionista, LocalDateTime data);

    Page<Consulta> findByPacienteId(Long idPaciente, Pageable pageable);

    @Query("SELECT c FROM Consulta c WHERE c.data = :data")
    Page<Consulta> findByDataConsulta(@Param("data") LocalDateTime data, Pageable pageable);
}